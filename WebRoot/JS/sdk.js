/*pub-1|2012-05-25 11:03:39*/if (!this.JSON) {
	this.JSON = {}
}
(function() {
	function f(n) {
		return n < 10 ? "0" + n : n
	}
	if (typeof Date.prototype.toJSON !== "function") {
		Date.prototype.toJSON = function(key) {
			return isFinite(this.valueOf()) ? this.getUTCFullYear() + "-"
					+ f(this.getUTCMonth() + 1) + "-" + f(this.getUTCDate())
					+ "T" + f(this.getUTCHours()) + ":"
					+ f(this.getUTCMinutes()) + ":" + f(this.getUTCSeconds())
					+ "Z" : null
		};
		String.prototype.toJSON = Number.prototype.toJSON = Boolean.prototype.toJSON = function(
				key) {
			return this.valueOf()
		}
	}
	var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, gap, indent, meta = {
		"\b" : "\\b",
		"\t" : "\\t",
		"\n" : "\\n",
		"\f" : "\\f",
		"\r" : "\\r",
		'"' : '\\"',
		"\\" : "\\\\"
	}, rep;
	function quote(string) {
		escapable.lastIndex = 0;
		return escapable.test(string) ? '"'
				+ string.replace(escapable, function(a) {
					var c = meta[a];
					return typeof c === "string" ? c : "\\u"
							+ ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
				}) + '"' : '"' + string + '"'
	}
	function str(key, holder) {
		var i, k, v, length, mind = gap, partial, value = holder[key];
		if (value && typeof value === "object"
				&& typeof value.toJSON === "function") {
			value = value.toJSON(key)
		}
		if (typeof rep === "function") {
			value = rep.call(holder, key, value)
		}
		switch (typeof value) {
		case "string":
			return quote(value);
		case "number":
			return isFinite(value) ? String(value) : "null";
		case "boolean":
		case "null":
			return String(value);
		case "object":
			if (!value) {
				return "null"
			}
			gap += indent;
			partial = [];
			if (Object.prototype.toString.apply(value) === "[object Array]") {
				length = value.length;
				for (i = 0; i < length; i += 1) {
					partial[i] = str(i, value) || "null"
				}
				v = partial.length === 0 ? "[]" : gap ? "[\n" + gap
						+ partial.join(",\n" + gap) + "\n" + mind + "]" : "["
						+ partial.join(",") + "]";
				gap = mind;
				return v
			}
			if (rep && typeof rep === "object") {
				length = rep.length;
				for (i = 0; i < length; i += 1) {
					k = rep[i];
					if (typeof k === "string") {
						v = str(k, value);
						if (v) {
							partial.push(quote(k) + (gap ? ": " : ":") + v)
						}
					}
				}
			} else {
				for (k in value) {
					if (Object.hasOwnProperty.call(value, k)) {
						v = str(k, value);
						if (v) {
							partial.push(quote(k) + (gap ? ": " : ":") + v)
						}
					}
				}
			}
			v = partial.length === 0 ? "{}" : gap ? "{\n" + gap
					+ partial.join(",\n" + gap) + "\n" + mind + "}" : "{"
					+ partial.join(",") + "}";
			gap = mind;
			return v
		}
	}
	if (typeof JSON.stringify !== "function") {
		JSON.stringify = function(value, replacer, space) {
			var i;
			gap = "";
			indent = "";
			if (typeof space === "number") {
				for (i = 0; i < space; i += 1) {
					indent += " "
				}
			} else {
				if (typeof space === "string") {
					indent = space
				}
			}
			rep = replacer;
			if (replacer
					&& typeof replacer !== "function"
					&& (typeof replacer !== "object" || typeof replacer.length !== "number")) {
				throw new Error("JSON.stringify")
			}
			return str("", {
				"" : value
			})
		}
	}
	if (typeof JSON.parse !== "function") {
		JSON.parse = function(text, reviver) {
			var j;
			function walk(holder, key) {
				var k, v, value = holder[key];
				if (value && typeof value === "object") {
					for (k in value) {
						if (Object.hasOwnProperty.call(value, k)) {
							v = walk(value, k);
							if (v !== undefined) {
								value[k] = v
							} else {
								delete value[k]
							}
						}
					}
				}
				return reviver.call(holder, key, value)
			}
			cx.lastIndex = 0;
			if (cx.test(text)) {
				text = text.replace(cx, function(a) {
					return "\\u"
							+ ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
				})
			}
			if (/^[\],:{}\s]*$/
					.test(text
							.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@")
							.replace(
									/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
									"]").replace(/(?:^|:|,)(?:\s*\[)+/g, ""))) {
				j = eval("(" + text + ")");
				return typeof reviver === "function" ? walk({
					"" : j
				}, "") : j
			}
			throw new SyntaxError("JSON.parse")
		}
	}
}());
(function() {
	var C = (function() {
		var D = false, E = location.hash;
		if (E.indexOf("#") == 0) {
			E = E.substr(1);
			D = ("&" + E + "&").indexOf("&log=1&") > -1
		}
		return D
	})();
	window.TOP = window.TOP
			|| {
				init : function(D) {
					if (!D.appKey) {
						TOP.log("cfg.appKey is required in init().", "error");
						return
					}
					if (!D.channelUrl) {
						TOP.log("cfg.channelUrl is required in init().",
								"error");
						return
					}
					TOP.fields.appKey = D.appKey;
					TOP.fields.channelUrl = D.channelUrl;
					if (D.auth) {
						TOP.auth.isAuth(function(E) {
							if (!E) {
								TOP.auth()
							}
						})
					} else {
						if (D.status) {
							TOP.auth.isLogin(function(E) {
								if (!E) {
									TOP.login()
								}
							})
						}
					}
					TOP.init = function() {
						TOP.log("init() can only be called one time.", "warn");
						return
					}
				},
				mix : function(F, G, D) {
					for ( var E in G) {
						if (D || typeof F[E] == "undefined") {
							F[E] = G[E]
						}
					}
					return F
				},
				namespace : function(E, F, D) {
					if (typeof E == "string") {
						E = A(E)
					}
					return TOP.mix(E, F, D)
				},
				guid : function(D) {
					D = D || "top";
					return D
							+ (Math.random() * (1 << 30)).toString(16).replace(
									".", "")
				},
				log : function(E, D) {
					D = D || "info";
					if (C && (typeof console != "undefined")) {
						console.log(D + ": " + E + " (" + B() + ")")
					}
				},
				getMainDomain : function() {
					var E = location.host;
					var D = E.split(".");
					if (D.length < 2) {
						return D.join(".")
					} else {
						return D.slice(-2).join(".")
					}
				}
			};
	function A(H) {
		var I = TOP, E = H ? H.split(".") : [];
		for ( var G = 0, D = E.length; G < D; G++) {
			var F = E[G];
			if (!I[F]) {
				I[F] = {}
			}
			I = I[F]
		}
		return I
	}
	function B() {
		var E = location.pathname, D = E.lastIndexOf("/");
		if (D == -1) {
			return E
		} else {
			return E.substr(D + 1)
		}
	}
})();
(function() {
	TOP.namespace("lang", {
		forEach : function(A, D) {
			if (!A || !D) {
				return
			}
			if (A.forEach) {
				A.forEach.call(A, D)
			} else {
				for ( var C = 0, B = A.length; C < len; C++) {
					D(A[C])
				}
			}
		},
		isPlainObject : function(A) {
			return A && Object.prototype.toString.call(A) === "[object Object]"
					&& "isPrototypeOf" in A
		},
		trim : function(A) {
			return A.replace(/^\s*|\s*$/g, "")
		},
		param : function(E, B, A) {
			if ((!TOP.lang.isPlainObject(E)) && (typeof E != "string")) {
				return ""
			}
			B = B || "&";
			A = A || "=";
			if (typeof E == "string") {
				E = TOP.lang.unparam(E, B, A)
			}
			var D = [];
			for ( var C in E) {
				var F = E[C];
				if (F !== null && typeof F != "undefined") {
					D.push(encodeURIComponent(C) + A + encodeURIComponent(F))
				}
			}
			D.sort();
			return D.join(B)
		},
		unparam : function(G, B, A) {
			B = B || "&";
			A = A || "=";
			var F = {}, D = G.split(B), C, E;
			for (C = 0; C < D.length; C++) {
				E = TOP.lang.trim(D[C]).split(A);
				if (E && (E.length == 2)) {
					F[decodeURIComponent(E[0])] = decodeURIComponent(E[1])
				}
			}
			return F
		}
	})
})();
(function() {
	var C = navigator.userAgent, E = "", A = "mobile", G = E, L = E, H, K = [
			6, 9 ], N, J, M = "{{version}}", I = "<!--[if IE " + M
			+ "]><s></s><![endif]-->", B = document.createElement("div"), O, D = {}, F = function(
			P) {
		var Q = 0;
		return parseFloat(P.replace(/\./g, function() {
			return (Q++ === 0) ? "." : ""
		}))
	};
	B.innerHTML = I.replace(M, "");
	O = B.getElementsByTagName("s");
	if (O.length > 0) {
		L = "ie";
		D[G = "trident"] = 0.1;
		if ((H = C.match(/Trident\/([\d.]*)/)) && H[1]) {
			D[G] = F(H[1])
		}
		for (N = K[0], J = K[1]; N <= J; N++) {
			B.innerHTML = I.replace(M, N);
			if (O.length > 0) {
				D[L] = N;
				break
			}
		}
	} else {
		if ((H = C.match(/AppleWebKit\/([\d.]*)/)) && H[1]) {
			D[G = "webkit"] = F(H[1]);
			if ((H = C.match(/Chrome\/([\d.]*)/)) && H[1]) {
				D[L = "chrome"] = F(H[1])
			} else {
				if ((H = C.match(/\/([\d.]*) Safari/)) && H[1]) {
					D[L = "safari"] = F(H[1])
				}
			}
			if (/ Mobile\//.test(C)) {
				D[A] = "apple"
			} else {
				if ((H = C.match(/NokiaN[^\/]*|Android \d\.\d|webOS\/\d\.\d/))) {
					D[A] = H[0].toLowerCase()
				}
			}
		} else {
			if ((H = C.match(/Presto\/([\d.]*)/)) && H[1]) {
				D[G = "presto"] = F(H[1]);
				if ((H = C.match(/Opera\/([\d.]*)/)) && H[1]) {
					D[L = "opera"] = F(H[1]);
					if ((H = C.match(/Opera\/.* Version\/([\d.]*)/)) && H[1]) {
						D[L] = F(H[1])
					}
					if ((H = C.match(/Opera Mini[^;]*/)) && H) {
						D[A] = H[0].toLowerCase()
					} else {
						if ((H = C.match(/Opera Mobi[^;]*/)) && H) {
							D[A] = H[0]
						}
					}
				}
			} else {
				if ((H = C.match(/MSIE\s([^;]*)/)) && H[1]) {
					D[G = "trident"] = 0.1;
					D[L = "ie"] = F(H[1]);
					if ((H = C.match(/Trident\/([\d.]*)/)) && H[1]) {
						D[G] = F(H[1])
					}
				} else {
					if ((H = C.match(/Gecko/))) {
						D[G = "gecko"] = 0.1;
						if ((H = C.match(/rv:([\d.]*)/)) && H[1]) {
							D[G] = F(H[1])
						}
						if ((H = C.match(/Firefox\/([\d.]*)/)) && H[1]) {
							D[L = "firefox"] = F(H[1])
						}
					}
				}
			}
		}
	}
	D.core = G;
	D.shell = L;
	D._numberify = F;
	TOP.ua = D
})();
(function() {
	var G = TOP.lang;
	var F = document;
	var C = "";
	TOP.namespace("dom", {
		get : function(H, J) {
			var I = TOP.dom.query(H, J);
			if (I.length) {
				I = I[0]
			} else {
				I = null
			}
			return I
		},
		query : function(H, J) {
			if (typeof H !== "string") {
				return [ H ]
			}
			J = J || F;
			var I = [], K, L = H.substr(0, 1);
			if (L == "#") {
				var K = F.getElementById(H.substr(1));
				if (K) {
					I.push(K)
				}
			} else {
				if (L == ".") {
					I = B(H.substr(1), J)
				} else {
					I = J.getElementsByTagName(H)
				}
			}
			return I
		},
		hasClass : function(H, I) {
			var K = TOP.dom.get(H);
			if (!K) {
				return false
			}
			var J = " " + K.className + " ";
			return J.indexOf(I) > -1
		},
		addClass : function(H, I) {
			var J = TOP.dom.get(H);
			if (!J) {
				return false
			}
			if (TOP.dom.hasClass(J, I)) {
				return
			} else {
				J.className = G.trim(J.className + " " + I)
			}
		},
		removeClass : function(H, K) {
			var L = TOP.dom.get(H), I;
			if ("undefined" === K) {
				I = ""
			} else {
				var J = new RegExp("\\s*" + K + "\\s*");
				I = L.className.replace(J, "")
			}
			if (L.className !== I) {
				L.className = I
			}
		},
		addStyleSheet : function(H, J) {
			var I;
			if (J && (J = J.replace("#", C))) {
				I = TOP.dom.get("#" + J)
			}
			if (I) {
				return
			}
			I = F.createElement("style");
			if (J) {
				I.id = J
			}
			TOP.dom.get("head").appendChild(I);
			if (I.styleSheet) {
				I.styleSheet.cssText = H
			} else {
				I.appendChild(F.createTextNode(H))
			}
		},
		style : function(H, J, K) {
			var I = arguments.length;
			if (I === 2) {
				if (typeof J === "string") {
					return A(H, J)
				} else {
					return E(H, J)
				}
			}
			return E(H, J, K)
		}
	});
	var B = F.getElementsByClassName ? function(H, I) {
		return I.getElementsByClassName(H)
	} : function(I, J) {
		var H = J.getElementsByTagName("*");
		H = D(H, function(K) {
			return TOP.dom.hasClass(K, I)
		});
		return H
	};
	var A = function(H, I) {
		var J = TOP.dom.get(H);
		return J.style[I]
	};
	var E = function(H, J, L) {
		var K = TOP.dom.get(H), I = arguments.length;
		if (!K) {
			return undefined
		}
		if (I === 3) {
			K.style[J] = L;
			return undefined
		}
		for ( var M in J) {
			E(K, M, J[M])
		}
		return undefined
	};
	function D(I, L) {
		var J = [];
		for ( var K = 0, H = I.length; K < H; K++) {
			var M = I[K];
			if (L(M)) {
				J.push(M)
			}
		}
		return J
	}
})();
(function() {
	var A = TOP.log, B = TOP.lang;
	TOP.namespace("io", {
		standardxdr : function(H, D, F, C) {
			H = H || "get";
			var E = new XMLHttpRequest(), G;
			E.onreadystatechange = function() {
				if (E.readyState == 4) {
					if (E.status == 200) {
						if (G) {
							clearTimeout(G)
						}
						var I = JSON.parse(E.responseText);
						C && C(I)
					}
				}
			};
			if (typeof F != "string") {
				F = B.param(F)
			}
			if (H.toLowerCase() == "get") {
				D = D + "?" + F;
				F = null
			}
			E.open(H, D, true);
			E.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			E.withCredentials = "true";
			E.send(F);
			G = setTimeout(function() {
				E.abort();
				A("xdr failure", "warning")
			}, 1000)
		}
	})
})();
(function() {
	var H = TOP.lang;
	var E = document, B = undefined, G = {};
	var F = "http://a.tbcdn.cn/apps/top/x/kio.swf", C = "top-xdr";
	function D(J, M, I) {
		var K = '<object id="'
				+ C
				+ '" type="application/x-shockwave-flash" data="'
				+ J
				+ '" width="0" height="0" ><param name="movie" value="'
				+ J
				+ '" /><param name="FlashVars" value="yid='
				+ M
				+ "&uid="
				+ I
				+ '&host=TOP.io.flashxdr" /><param name="allowScriptAccess" value="always" /></object>';
		var L = E.createElement("div");
		L.style.position = "absolute";
		L.style.left = "1px";
		L.style.top = "1px";
		E.body.insertBefore(L, E.body.firstChild);
		L.innerHTML = K;
		D = function() {
			return
		}
	}
	function A(M, K, L, I) {
		var J = this;
		if (!(J instanceof A)) {
			return new A(M, K, L, I)
		}
		J.method = M;
		J.url = K;
		J.params = L;
		J.cb = I;
		J.send()
	}
	A.prototype = {
		send : function() {
			var I = this;
			D(F, C, 1);
			if (!B) {
				setTimeout(function() {
					I.send()
				}, 200);
				return
			}
			I.uid = TOP.guid();
			G[I.uid] = I;
			B.send(I.url, {
				id : I.uid,
				uid : I.uid,
				method : I.method.toUpperCase(),
				data : I.params
			})
		},
		abort : function() {
			B.abort(this.uid)
		},
		_xdrResponse : function(K, L) {
			var I = this;
			var J;
			I.responseText = decodeURI(L.c.responseText);
			switch (K) {
			case "success":
				I.cb && I.cb(JSON.parse(I.responseText));
				J = {
					status : 200,
					statusText : "success"
				};
				delete G[L.id];
				break;
			case "abort":
				delete G[L.id];
				break;
			case "timeout":
			case "transport error":
			case "failure":
				J = {
					status : 500,
					statusText : K
				};
				delete G[L.id];
				break
			}
		}
	};
	A.applyTo = function(L, O, K) {
		var J = O.split(".").slice(1), N = A;
		for ( var M = 0, I = J.length; M < I; M++) {
			N = N[J[M]]
		}
		N.apply(null, K)
	};
	A.xdrReady = function() {
		B = E.getElementById(C)
	};
	A.xdrResponse = function(I, K, L) {
		var J = G[K.uid];
		J && J._xdrResponse(I, K, L)
	};
	TOP.namespace("io", {
		flashxdr : A
	})
})();
(function() {
	var B = TOP.log, E = TOP.lang;
	var D = document, A = document.getElementsByTagName("head")[0];
	TOP.namespace("io", {
		getStyle : function(G) {
			var H = D.createElement("link"), F = TOP.guid();
			H.href = G;
			H.rel = "stylesheet";
			H.id = F;
			A.appendChild(H);
			return H
		},
		getScript : function(H, F) {
			var I = D.createElement("script"), G = TOP.guid();
			I.src = H;
			I.id = G;
			I.charset = "utf-8";
			F && C(I, F);
			A.appendChild(I);
			return I
		},
		jsonp : function(H, J, F) {
			var G = TOP.guid(), I = "TOP.io.jsonpCbs." + G, H = H
					+ "?callback=" + I;
			if (J) {
				H = H + "&" + E.param(J)
			}
			if (H.length > 2000) {
				B("JSONP only support a maximum of 2000 bytes of input.",
						"error");
				return
			}
			TOP.io.jsonpCbs[G] = function(K) {
				F && F(K);
				delete TOP.io.jsonpCbs[G]
			};
			TOP.io.getScript(H)
		},
		jsonpCbs : {},
		get : function(G, H, F) {
			TOP.io.xdr("get", G, H, F)
		},
		post : function(G, H, F) {
			TOP.io.xdr("post", G, H, F)
		},
		xdr : function(J, G, I, F) {
			if (typeof XMLHttpRequest == "undefined") {
				TOP.io.xdr = TOP.io.flashxdr
			} else {
				var H = new XMLHttpRequest();
				if (H && "withCredentials" in H) {
					TOP.io.xdr = TOP.io.standardxdr
				} else {
					TOP.io.xdr = TOP.io.flashxdr
				}
			}
			TOP.io.xdr(J, G, I, F)
		}
	});
	function C(G, F) {
		var H = F;
		F = function() {
			H && H();
			G.parentNode.removeChild(G)
		};
		if (D.addEventListener) {
			G.addEventListener("load", F, false)
		} else {
			G.onreadystatechange = function() {
				var I = G.readyState;
				if (/loaded|complete/i.test(I)) {
					F()
				}
			}
		}
	}
})();
(function() {
	var E = document, A = 24 * 60 * 60 * 1000, C = encodeURIComponent, D = decodeURIComponent;
	function B(F) {
		return (typeof F == "string") && F !== ""
	}
	TOP.namespace("cookie", {
		get : function(H) {
			var G, F;
			if (B(H)) {
				if ((F = String(E.cookie).match(
						new RegExp("(?:^| )" + H + "(?:(?:=([^;]*))|;|$)")))) {
					G = F[1] ? D(F[1]) : ""
				}
			}
			return G
		},
		set : function(H, M, F, I, K, J) {
			var L = String(C(M)), G = F;
			if (typeof G === "number") {
				G = new Date();
				G.setTime(G.getTime() + F * A)
			}
			if (G instanceof Date) {
				L += "; expires=" + G.toUTCString()
			}
			if (B(I)) {
				L += "; domain=" + I
			}
			if (B(K)) {
				L += "; path=" + K
			}
			if (J) {
				L += "; secure"
			}
			E.cookie = H + "=" + L
		},
		remove : function(F, G, I, H) {
			TOP.cookie.set(F, "", -1, G, I, H)
		}
	})
})();
(function() {
	var F = TOP.dom, A = TOP.fields, B = TOP.ua;
	var E = window, D = document;
	TOP.namespace("", {
		ready : function(H) {
			if (D.readyState == "complete") {
				H(TOP)
			} else {
				G.push(H)
			}
		},
		closeWindow : function(H) {
			if (!B.ie) {
				return H.close()
			} else {
				if (6 < B.ie) {
					H.open("", "_self", "");
					H.close()
				} else {
					H.opener = null;
					H.close()
				}
			}
		}
	});
	var G = [];
	function C() {
		var H = G;
		G = [];
		while (H.length) {
			H.shift()(TOP)
		}
	}
	(function() {
		var H = D.documentElement.doScroll;
		eventType = H ? "onreadystatechange" : "DOMContentLoaded";
		if (D.addEventListener) {
			D.addEventListener(eventType, C, false);
			E.addEventListener("load", C, false)
		} else {
			D.attachEvent(eventType, function() {
				if (D.readyState == "complete") {
					C()
				}
			});
			E.attachEvent("onload", C)
		}
	})()
})();
(function() {
	var A = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	TOP.namespace("base64", {
		decode : function(E) {
			var C = "";
			var L, J, H;
			var K, I, G, F;
			var D = 0;
			E = E.replace(/[^A-Za-z0-9\+\/\=]/g, "");
			while (D < E.length) {
				K = A.indexOf(E.charAt(D++));
				I = A.indexOf(E.charAt(D++));
				G = A.indexOf(E.charAt(D++));
				F = A.indexOf(E.charAt(D++));
				L = (K << 2) | (I >> 4);
				J = ((I & 15) << 4) | (G >> 2);
				H = ((G & 3) << 6) | F;
				C = C + String.fromCharCode(L);
				if (G != 64) {
					C = C + String.fromCharCode(J)
				}
				if (F != 64) {
					C = C + String.fromCharCode(H)
				}
			}
			C = B(C);
			return C
		}
	});
	function B(C) {
		var D = "";
		var E = 0;
		var F = c1 = c2 = 0;
		while (E < C.length) {
			F = C.charCodeAt(E);
			if (F < 128) {
				D += String.fromCharCode(F);
				E++
			} else {
				if ((F > 191) && (F < 224)) {
					c2 = C.charCodeAt(E + 1);
					D += String.fromCharCode(((F & 31) << 6) | (c2 & 63));
					E += 2
				} else {
					c2 = C.charCodeAt(E + 1);
					c3 = C.charCodeAt(E + 2);
					D += String.fromCharCode(((F & 15) << 12)
							| ((c2 & 63) << 6) | (c3 & 63));
					E += 3
				}
			}
		}
		return D
	}
})();
(function() {
	TOP.namespace("fields", {
		widgetUrl : "http://gw.api.taobao.com/widget",
		authUrl : "https://oauth.taobao.com/authorize",
		ie6AuthUrl : "http://container.api.taobao.com/container",
		logoutUrl : "https://oauth.taobao.com/logoff",
		uiUrl : "http://a.tbcdn.cn/apps/top/x/ui",
		xdFlashUrl : "http://a.tbcdn.cn/apps/top/x/postmsg.swf",
		swfobjectUrl : "http://a.tbcdn.cn/apps/top/x/swfobject.js",
		appKey : null,
		channelUrl : null,
		isMainFrame : false,
		mainFrame : window.top,
		frameId : null,
		iframes : []
	})
})();
(function() {
	var F = TOP.dom, A = TOP.fields;
	TOP.namespace("ev", {
		add : function(M, L, K) {
			if (typeof M == "string") {
				var J = F.query(M);
				if (J.length) {
					for ( var I = 0, H = J.length; I < H; I++) {
						B(J[I], L, K)
					}
				} else {
					D(M, L, K)
				}
			} else {
				B(M, L, K)
			}
		},
		remove : function(M, L, K) {
			if (typeof M == "string") {
				var J = F.query(M);
				if (J.length) {
					for ( var I = 0, H = J.length; I < H; I++) {
						G(J[I], L, K)
					}
				} else {
					E(M, L, K)
				}
			} else {
				G(M, L, K)
			}
		},
		fire : function(N, K, L, M) {
			if (C[N] && C[N][K]) {
				var J = C[N][K];
				for ( var I = 0, H = J.length; I < H; I++) {
					J[I](L)
				}
			}
			TOP.ev.afterFire(N, K, L, M)
		},
		afterFire : function(K, H, I, J) {
		},
		stopPropagation : function(H) {
			if (!H) {
				return
			}
			if (H.stopPropagation) {
				H.stopPropagation()
			}
			H.cancelBubble = true
		},
		preventDefault : function(H) {
			if (!H) {
				return
			}
			if (H.preventDefault) {
				H.preventDefault()
			} else {
				H.returnValue = false
			}
		}
	});
	TOP.ev.on = TOP.ev.add;
	function B(J, I, H) {
		window.addEventListener ? J.addEventListener(I, H, false) : J
				.attachEvent("on" + I, H)
	}
	function G(J, I, H) {
		window.addEventListener ? J.removeEventListener(I, H, false) : J
				.dettachEvent("on" + I, H)
	}
	var C = {};
	function D(J, I, H) {
		if (!C[J]) {
			C[J] = {}
		}
		if (!C[J][I]) {
			C[J][I] = []
		}
		C[J][I].push(H)
	}
	function E(M, L, K) {
		if (!C[M]) {
			return
		}
		if (!C[M][L]) {
			return
		}
		var J = C[M][L];
		for ( var I = 0, H = J.length; I < H; I++) {
			if (J[I] == K) {
				J.splice(I, 1);
				return
			}
		}
	}
})();
(function() {
	var B = TOP.ua, F = TOP.io, C = TOP.ev, E = TOP.lang, A = TOP.fields;
	TOP
			.namespace(
					"xd",
					{
						init : function() {
							if (window.postMessage && !B.ie) {
								TOP.xd.html5Init()
							} else {
								TOP.xd.flashInit()
							}
						},
						postMessage : function(G, H) {
						},
						html5Init : function() {
							TOP.xd.postMessage = function(H, I) {
								I = I || A.mainFrame;
								I.postMessage(H, "*")
							};
							C.afterFire = D;
							var G = function(I) {
								var H = I.data;
								C.fire(H.name, H.ev, H.data, I.source)
							};
							C.add(window, "message", G)
						},
						flashInit : function() {
							C.afterFire = function(J, G, H, I) {
								TOP.xd.flashPostFns.push({
									name : J,
									ev : G,
									data : H,
									fromId : "_" + A.frameId
								})
							};
							if (typeof swfobject == "undefined") {
								F.getScript(A.swfobjectUrl, TOP.xd.insertSwf)
							} else {
								TOP.xd.insertSwf()
							}
						},
						flashPostFns : [],
						insertSwf : function() {
							TOP
									.ready(function() {
										var K = A.frameId, I = document
												.createElement("div");
										I.id = "top-xd-swf-box-" + K;
										document.body.appendChild(I);
										I.style.position = "absolute";
										I.style.left = "-1000px";
										I.style.top = "-1000px";
										var G = {};
										G.jsentry = "TOP.xd.swfCb";
										G.swfid = "top-xd-swf-" + K;
										G.name = "_" + K;
										var J = {};
										J.allowscriptaccess = "always";
										var H = {};
										swfobject
												.embedSWF(
														A.xdFlashUrl,
														"top-xd-swf-box-" + K,
														"1",
														"1",
														"9.0.0",
														false,
														G,
														J,
														H,
														function(L) {
															if (!L.success) {
																alert("\u60a8\u5f53\u524d\u7684\u6d4f\u89c8\u5668\u7248\u672c\u8fc7\u4f4e\uff0c\u8bf7\u5347\u7ea7\u6d4f\u89c8\u5668\u6216\u5b89\u88c5flash\u64ad\u653e\u5668\u3002");
																TOP
																		.log(
																				"widget interaction need flash player or new browser.",
																				"error");
																return
															}
															TOP.xd.swfObj = L.ref
														})
									})
						},
						swfObj : null,
						swfCb : function(H, I) {
							switch (I.type) {
							case "swfReady":
								TOP.xd.postMessage = function(K, L) {
									L = L || "_" + A.mainFrameId;
									TOP.xd.swfObj.send(K, L)
								};
								C.afterFire = D;
								if (TOP.xd.flashPostFns.length) {
									while (TOP.xd.flashPostFns.length) {
										var G = TOP.xd.flashPostFns.shift();
										TOP.xd.postMessage(G)
									}
								}
								break;
							case "message":
								var J = I.msg;
								if (J.fromId != "_" + A.frameId) {
									C.fire(J.name, J.ev, J.data, J.fromId)
								}
								break
							}
						}
					});
	function D(N, J, K, M) {
		var L = {
			name : N,
			ev : J,
			data : K,
			fromId : "_" + A.frameId
		};
		if (A.isMainFrame) {
			for ( var H = 0, G = A.iframes.length; H < G; H++) {
				var I = A.iframes[H];
				if (I != M) {
					TOP.xd.postMessage(L, I)
				}
			}
		} else {
			if (!M) {
				TOP.xd.postMessage(L)
			}
		}
	}
})();
(function() {
	var G = TOP.lang, E = TOP.cookie, D = TOP.ua, A = TOP.fields, F = TOP.ev;
	var B = 24 * 60 * 60, C = 2 * 60;
	TOP.namespace("", {
		auth : function() {
			if (!A.appKey) {
				TOP.log("init() must called first.", "error");
				return
			}
			var J = {
				response_type : "token",
				redirect_uri : A.channelUrl
			};
			var I;
			if (D.ie == 6) {
				J.appkey = A.appKey;
				I = A.ie6AuthUrl
			} else {
				J.client_id = A.appKey;
				I = A.authUrl
			}
			I = I + "?" + G.param(J);
			window.top.open(I)
		},
		login : function() {
			TOP.auth()
		},
		logout : function() {
			var I = [ A.logoutUrl,
					"?client_id=${clientId}&redirect_uri=${redirectUrl}",
					"?action=logout" ].join("").replace(/\${clientId}/g,
					A.appKey).replace(/\${redirectUrl}/g, A.channelUrl);
			parent.location.href=I;
		}
	});
	TOP
			.namespace(
					"auth",
					{
						isAuth : function(I, K) {
							if (!I) {
								TOP
										.log(
												"param cb is required in TOP.auth.isAuth()",
												"error")
							}
							var J = E.get("session");
							if (J) {
								TOP.auth.isLogin(I, K)
							} else {
								I(false)
							}
						},
						isLogin : function(I, L) {
							if (!I) {
								TOP
										.log(
												"param cb is required in TOP.auth.isLogin()",
												"error")
							}
							var K = E.get("topCreatedTime"), J = E
									.get("topStatus");
							if (typeof K === "undefined"
									|| TOP.auth.isExpired(K)) {
								L = true
							}
							if (L) {
								var M = I;
								I = function(O) {
									if (O.length) {
										if (O[0]) {
											TOP.auth.setUser.apply(window, O);
											M(true)
										} else {
											TOP.auth.deleteUser();
											M(false)
										}
									} else {
										var N = O.error_response;
										if (N
												&& (N.code === 26 || N.code === 27)) {
											TOP.auth.deleteUser();
											M(false)
										} else {
											TOP
													.log("Error exists when check if the user has login!")
										}
									}
								};
								H(null, I)
							} else {
								I(J === "1" ? true : false)
							}
						},
						getLoginStatus : function(J, I) {
							if (!I) {
								TOP
										.log(
												"param cb is required in TOP.auth.getLoginStatus()",
												"error")
							}
							H(J, I)
						},
						setUser : function(J, K, I) {
							J = J ? "1" : "0";
							E.set("topStatus", J);
							K && E.set("topId", K);
							I && E.set("topNick", I);
							TOP.auth.updateCreatedTimeCookie(new Date()
									.getTime())
						},
						deleteUser : function() {
							E.set("topStatus", "0");
							E.remove("topId");
							E.remove("topNick");
							TOP.auth.updateCreatedTimeCookie(new Date()
									.getTime())
						},
						updateCreatedTimeCookie : function(I) {
							E.set("topCreatedTime", I)
						},
						isExpired : function(I) {
							var J = new Date().getTime() - I;
							return window.parseInt(J / 1000) > C
						},
						afterAuth : function(O) {
							var M, K, L, J, P, I;
							O = G.unparam(O);
							if (D.ie == 6) {
								M = O.top_session;
								var N = G.unparam(TOP.base64
										.decode(O.top_parameters));
								expireIn = N.expires_in;
								P = N.visitor_id;
								I = N.visitor_nick
							} else {
								M = O.access_token;
								expireIn = O.expires_in;
								P = O.taobao_user_id;
								I = O.taobao_user_nick
							}
							M && (E.set("session", M, expireIn / B));
							A.prevSession = M;
							if (P) {
								TOP.auth.afterLogin(P, I)
							}
							F.fire("auth", "sessionChange", M);
							F.fire("auth", "auth", M)
						},
						afterLogin : function(J, I) {
							TOP.auth.setUser("1", J, I);
							F.fire("auth", "login", {
								status : "1",
								id : J,
								nick : I
							})
						},
						afterLogout : function() {
							this.clearCookie();
							F.fire("auth", "logout")
						},
						clearCookie : function() {
							var J = [ "session", "topStatus", "topId",
									"topNick", "topCreatedTime" ];
							for ( var K = 0, I = J.length; K < I; K++) {
								E.remove(J[K])
							}
						}
					});
	function H(J, I) {
		if (!A.appKey) {
			TOP.log("init() must called first.", "error")
		}
		var K = {
			method : "taobao.widget.loginstatus.get"
		};
		J && (K.nick = J);
		TOP.api(K, I)
	}
})();
(function() {
	var A = TOP.fields, E = TOP.lang, C = TOP.cookie, F = TOP.io, D = TOP.auth;
	TOP
			.namespace(
					"",
					{
						api : function(L, G) {
							var H = A.widgetUrl, J = 1;
							if (arguments.length == 4) {
								var I = arguments[0], M = arguments[1], L = arguments[2], G = arguments[3];
								if (I == "tql") {
									J = 0;
									if (typeof L == "string") {
										L = {
											ql : L
										}
									}
								}
							} else {
								if (typeof L == "string") {
									var K = E.unparam(L);
									if (!K.method) {
										J = 0;
										if (!K.ql) {
											L = {
												ql : L
											}
										}
									}
								} else {
									if (!L.method) {
										J = 0
									}
								}
								var M = "get"
							}
							if (J) {
								H += "/rest"
							} else {
								H += "/tql/2.0/json"
							}
							L = B(L);
							if (M == "get") {
								F.jsonp(H, L, G)
							} else {
								F.post(H, L, G)
							}
						}
					});
	function B(H) {
		if (typeof H == "string") {
			H = E.unparam(H)
		}
		H.app_key = A.appKey;
		if (!H.timestamp) {
			H.timestamp = C.get("timestamp") || A.timestamp
		}
		if (!H.sign) {
			H.sign = C.get("sign") || A.sign
		}
		if (!H.session) {
			var G = C.get("session") || A.session;
			if (G) {
				H.session = G
			}
		}
		return H
	}
})();
(function() {
	var B = TOP.log, D = TOP.io, A = TOP.fields, C = TOP.auth;
	TOP.namespace("", {
		ui : function(H, F, I) {
			if (!H) {
				B("name is required for ui().", "error");
				return
			}
			var G = F.guid ? F.guid : TOP.guid();
			var E = F.version ? F.version : "1.0";
			if (TOP.ui[H]) {
				var J = TOP.ui[H](G, F);
				I && I(J)
			} else {
				D.getScript(A.uiUrl + "/" + H + "/" + E + "/" + H + ".js",
						function(K) {
							var L = TOP.ui[H](G, F);
							I && I(L)
						})
			}
		}
	})
})();
(function() {
	var H = TOP.lang, C = TOP.ua, G = TOP.dom, E = TOP.ev, B = TOP.cookie, A = TOP.fields, D = TOP.auth;
	var F = document;
	TOP.namespace("uihelper", {
		insertIframe : function(K, J, I) {
			var M = F.createElement("iframe");
			if (J.indexOf("#") == -1) {
				J += "#"
			} else {
				J += "&"
			}
			J += "timestamp=" + B.get("timestamp") + "&";
			J += "sign=" + B.get("sign") + "&";
			var N = B.get("session");
			if (N) {
				J += "session=" + N + "&"
			}
			var L = B.get("topId");
			if (L) {
				J += "topId=" + L + "&"
			}
			J += "frame_id=" + K + "&";
			J += "main_frame_id=" + A.frameId + "&";
			J += "app_key=" + A.appKey;
			M.src = J;
			M.frameBorder = "0";
			if (I.container) {
				G.get(I.container).appendChild(M)
			}
			E.add(M, "load", function() {
				if (window.postMessage && !C.ie) {
					A.iframes.push(M.contentWindow)
				} else {
					A.iframes.push("_" + K)
				}
			});
			return M
		}
	})
})();
(function() {
	TOP.namespace("channel", {
		transport : function() {
			var F, E, D, C, B;
			F = window.location.search.substr(1);
			E = window.location.hash.substr(1);
			B = E.match(/state=([^&]+)/) || F.match(/state=([^&]+)/)
					|| F.match(/action=([^&]+)/);
			D = B ? B[1] : "auth";
			C = (0 === E.length) ? F : E;
			A.dispatch(D, C);
			TOP.closeWindow(window)
		}
	});
	var A = {
		routes : {
			logout : "afterLogout",
			auth : "afterAuth"
		},
		dispatch : function(D, C) {
			var B = this.routes;
			if (typeof D === "string" && B[D]) {
				window.opener && window.opener.TOP && window.opener.TOP.auth
						&& window.opener.TOP.auth[B[D]](C)
			}
		}
	}
})();
(function() {
	var F = TOP.lang, D = TOP.ev, C = TOP.cookie, A = TOP.fields;
	if (window.location.href.indexOf(A.uiUrl) == -1) {
		A.isMainFrame = true;
		A.frameId = TOP.guid();
		setInterval(function() {
			var G = C.get("session");
			if (G != A.prevSession) {
				A.prevSession = G;
				D.fire("auth", "sessionChange", G)
			}
		}, 2 * 60 * 1000);
		B()
	} else {
		var E = window.location.hash.substr(1);
		E = F.unparam(E);
		A.frameId = E.frame_id;
		A.mainFrameId = E.main_frame_id;
		A.timestamp = E.timestamp;
		A.sign = E.sign;
		A.topId = E.topId;
		if (E.session) {
			A.session = E.session
		}
		A.appKey = E.app_key;
		D.add("auth", "sessionChange", function(G) {
			A.session = G
		})
	}
	TOP.xd.init();
	function B() {
		var M = document.getElementById("top-sdk");
		if (M) {
			var O = M.getAttribute("data-init");
			if (O) {
				var J = F.unparam(O);
				TOP.init(J);
				var N = M.getAttribute("data-ui");
				if (N) {
					N = N.split(",");
					for ( var I = 0, K = N.length; I < K; I++) {
						var G = N[I];
						var L = document.getElementById("top-" + G);
						if (L) {
							var H = L.getAttribute("data-param");
							TOP.ui(G, F.unparam(H))
						}
					}
				}
			}
		}
	}
})();