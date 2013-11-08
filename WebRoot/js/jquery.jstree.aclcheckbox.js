/*
 * 自定义jsTree插件
 * 在树节点上定义如下状态显示：
 * 未定义 － 正常显示
 * 拒绝 － checkbox上打叉
 * 许可 － checkbox上打勾
 * 继承 － 背景显示成灰色
 */
(function($)
{
	$.jstree.plugin("aclcheckbox", {
		__init : function()
		{
			this.select_node = this.deselect_node = this.deselect_all = $.noop;
			this.get_selected = this.get_checked;

			this.get_container().bind("open_node.jstree create_node.jstree clean_node.jstree", $.proxy(function(e, data)
			{
				this._prepare_checkboxes(data.rslt.obj);
			}, this)).bind("loaded.jstree", $.proxy(function(e)
			{
				this._prepare_checkboxes();

				// 插入样式
				var css = false;
				var url = "org/acl/style/style.css";
				if (document.createStyleSheet) {
					css = document.createStyleSheet(url);
				} else {
					css = document.createElement("link");
					css.type = "text/css";
					css.rel = "stylesheet";
					css.href = url;

					document.getElementsByTagName("head")[0].appendChild(css);
				}
			}, this)).delegate("a", "click.jstree", $.proxy(function(e)
			{
				// if(this._get_node(e.target).hasClass("jstree-checked")) {
				// this.uncheck_node(e.target); }
				// else { this.check_node(e.target); }
				if (this._get_node(e.target).hasClass("jstree-permit")) {
					this.deny_node(e.target);
				} else {
					this.permit_node(e.target);
				}
				if (this.data.ui) {
					this.save_selected();
				}
				if (this.data.cookies) {
					this.save_cookie("select_node");
				}
				e.preventDefault();
			}, this));
		},
		__destroy : function()
		{
			this.get_container().find(".jstree-checkbox").remove();
		},
		_fn : {
			_prepare_checkboxes : function(obj)
			{// 重新定义checkbox默认状态
				obj = !obj || obj == -1 ? this.get_container() : this._get_node(obj);
				var c, _this = this, t;
				obj.each(function()
				{
					t = $(this);
					c = t.is("li") && t.hasClass("jstree-permit") ? "jstree-permit" : "jstree-normal";
					t.find("a").not(":has(.jstree-checkbox)").prepend("<ins class='jstree-checkbox'>&#160;</ins>").parent().not(".jstree-permit, .jstree-normal").addClass(c);
				});
				if (obj.is("li")) {
					this._repair_state(obj);
				} else {
					obj.find("> ul > li").each(function()
					{
						_this._repair_state(this);
					});
				}
			},
			change_state : function(obj, state)
			{
				obj = this._get_node(obj);
				state = (state === false || state === true) ? state : obj.hasClass("jstree-checked");
				if (state) {
					obj.find("li").andSelf().removeClass("jstree-checked jstree-undetermined").addClass("jstree-unchecked");
				} else {
					obj.find("li").andSelf().removeClass("jstree-unchecked jstree-undetermined").addClass("jstree-checked");
					if (this.data.ui) {
						this.data.ui.last_selected = obj;
					}
					this.data.checkbox.last_selected = obj;
				}
				obj.parentsUntil(".jstree", "li").each(function()
				{
					var $this = $(this);
					if (state) {
						if ($this.children("ul").children(".jstree-checked, .jstree-undetermined").length) {
							$this.parentsUntil(".jstree", "li").andSelf().removeClass("jstree-checked jstree-unchecked").addClass("jstree-undetermined");
							return false;
						} else {
							$this.removeClass("jstree-checked jstree-undetermined").addClass("jstree-unchecked");
						}
					} else {
						if ($this.children("ul").children(".jstree-unchecked, .jstree-undetermined").length) {
							$this.parentsUntil(".jstree", "li").andSelf().removeClass("jstree-checked jstree-unchecked").addClass("jstree-undetermined");
							return false;
						} else {
							$this.removeClass("jstree-unchecked jstree-undetermined").addClass("jstree-checked");
						}
					}
				});
				if (this.data.ui) {
					this.data.ui.selected = this.get_checked();
				}
				this.__callback(obj);
			},
			permit_node : function(obj)
			{// 把节点设置成许可状态（同时设置所有祖先节点为许可状态）
				obj = this._get_node(obj);
				obj.removeClass("jstree-normal jstree-deny jstree-extend").addClass("jstree-permit");
				obj.parentsUntil(".jstree", "li").each(function()
				{
					var t = $(this);
					t.removeClass("jstree-normal jstree-deny jstree-extend").addClass("jstree-permit");
				});
			},
			deny_node : function(obj)
			{// 把节点设置成拒绝状态
				obj = this._get_node(obj);
				obj.removeClass("jstree-normal jstree-permit jstree-extend").addClass("jstree-deny");
			},
			extends_node : function(obj)
			{// 把节点设置成继承状态
				obj = this._get_node(obj);
				obj.addClass("jstree-extend");
			},
			cancel_node : function(obj)
			{// 把节点设置成取消状态
				obj = this._get_node(obj);
				obj.removeClass("jstree-deny jstree-permit jstree-extend").addClass("jstree-normal");
			},
			permit_all : function(node)
			{// 把指定节点或所有节点设置成许可状态
				var _this = this;
				if (!node) {
					node = this.get_container();
					node.find("li").each(function()
					{
						_this.permit_node(this);
					});
				} else {
					node.find("li").andSelf().each(function()
					{
						_this.permit_node(this);
					});
				}
			},
			deny_all : function(node)
			{// 把指定节点或所有节点设置成拒绝状态
				var _this = this;
				if (!node) {
					node = this.get_container();
					node.find("li").each(function()
					{
						_this.deny_node(this);
					});
				} else {
					node.find("li").andSelf().each(function()
					{
						_this.deny_node(this);
					});
				}
			},
			cancel_all : function(node)
			{// 把指定节点或所有节点设置成取消状态
				var _this = this;
				if (!node) {
					node = this.get_container();
					node.find("li").each(function()
					{
						_this.cancel_node(this);
					});
				} else {
					node.find("li").andSelf().each(function()
					{
						_this.cancel_node(this);
					});
				}
			},
			extends_all : function(node)
			{// 把指定节点或所有节点设置成继承状态
				var _this = this;
				if (!node) {
					node = this.get_container();
					node.find("li").each(function()
					{
						_this.extends_node(this);
					});
				} else {
					node.find("li").andSelf().each(function()
					{
						_this.extends_node(this);
					});
				}
			},
			get_all_auths_node : function()
			{// 返回树本身被授权的节点（授权许可、拒绝和继承）
				return this.get_container().find("li.jstree-permit,li.jstree-deny,li.jstree-extend");
			},
			check_node : function(obj)
			{
				this.change_state(obj, false);
			},
			uncheck_node : function(obj)
			{
				this.change_state(obj, true);
			},
			check_all : function()
			{
				var _this = this;
				this.get_container().children("ul").children("li").each(function()
				{
					_this.check_node(this, false);
				});
			},
			uncheck_all : function()
			{
				var _this = this;
				this.get_container().children("ul").children("li").each(function()
				{
					_this.change_state(this, true);
				});
			},
			is_checked : function(obj)
			{
				obj = this._get_node(obj);
				return obj.length ? obj.is(".jstree-checked") : false;
			},
			get_checked : function(obj)
			{
				obj = !obj || obj === -1 ? this.get_container() : this._get_node(obj);
				return obj.find("> ul > .jstree-checked, .jstree-undetermined > ul > .jstree-checked");
			},
			get_unchecked : function(obj)
			{
				obj = !obj || obj === -1 ? this.get_container() : this._get_node(obj);
				return obj.find("> ul > .jstree-unchecked, .jstree-undetermined > ul > .jstree-unchecked");
			},

			show_checkboxes : function()
			{
				this.get_container().children("ul").removeClass("jstree-no-checkboxes");
			},
			hide_checkboxes : function()
			{
				this.get_container().children("ul").addClass("jstree-no-checkboxes");
			},

			_repair_state : function(obj)
			{
				/*
				 * obj = this._get_node(obj); if(!obj.length) { return; } var a =
				 * obj.find("> ul > .jstree-checked").length, b = obj.find("> ul >
				 * .jstree-undetermined").length, c = obj.find("> ul >
				 * li").length;
				 * 
				 * if(c === 0) { if(obj.hasClass("jstree-undetermined")) {
				 * this.check_node(obj); } } else if(a === 0 && b === 0) {
				 * this.uncheck_node(obj); } else if(a === c) {
				 * this.check_node(obj); } else {
				 * obj.parentsUntil(".jstree","li").removeClass("jstree-checked
				 * jstree-unchecked").addClass("jstree-undetermined"); }
				 */
			},
			reselect : function()
			{
				if (this.data.ui) {
					var _this = this, s = this.data.ui.to_select;
					s = $.map($.makeArray(s), function(n)
					{
						return "#" + n.toString().replace(/^#/, "").replace('\\/', '/').replace('/', '\\/');
					});
					this.deselect_all();
					$.each(s, function(i, val)
					{
						_this.check_node(val);
					});
					this.__callback();
				}
			}
		}
	});
})(jQuery);
// */
