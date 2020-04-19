webpackJsonp([1],{"47Ps":function(t,e){},"64cm":function(t,e){},NHnr:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=a("7+uW"),n=(a("87tG"),a("miEh"),a("Oq2I"),a("80cc"),{name:"throughput_guage",props:["throughput","valueName"],data:function(){return{option:{series:[{startAngle:200,endAngle:-20,type:"gauge",max:500,data:[{value:this.throughput,name:this.valueName}]}]}}},mounted:function(){var t=this;setInterval(function(){t.option.series[0].data[0].value=t.throughput},5e3)}}),s={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{attrs:{id:"throughput_guage"}},[e("v-chart",{attrs:{theme:"macarons",options:this.option}})],1)},staticRenderFns:[]};var i=a("VU/8")(n,s,!1,function(t){a("47Ps")},"data-v-7d8e26d6",null).exports,r=(a("4UDB"),{name:"throughput_history",props:["history"],data:function(){return{option:{xAxis:{type:"category",data:this.history.dataNames,splitLine:{show:!1}},yAxis:{type:"value",scale:!0,boundaryGap:[0,"100%"],splitLine:{show:!1}},series:[{data:this.history.dataValues,type:"line"}]}}},mounted:function(){var t=this;setInterval(function(){t.option.series[0].data=t.history.dataValues,t.option.xAxis.data=t.history.dataNames},5e3)}}),u={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{attrs:{id:"throughput_history"}},[e("v-chart",{attrs:{theme:"macarons",options:this.option}})],1)},staticRenderFns:[]};var h={name:"throughput",components:{throughputGuage:i,throughputHistory:a("VU/8")(r,u,!1,function(t){a("fP8A")},"data-v-7b728698",null).exports},data:function(){return{totalValueName:"总吞吐量",valueName:"吞吐量",serviceThroughputList:[{host:"example1",status:!1,historyTotalThroughput:{dataNames:[],dataValues:[]},dataList:[{itemName:"throughput1",averageCost:"0",lastCost:0,throughput:"10.1"}]},{host:"example2",status:!1,historyTotalThroughput:{dataNames:[],dataValues:[]},dataList:[{itemName:"throughput2",averageCost:"0.0000",lastCost:0,throughput:"10.2"}]}]}},methods:{getContext:function(){var t=this.$route.path.split("/"),e="";return t.length>2&&(e="/"+t[1]),console.log(e),e},openManagement:function(){var t=this;this.$http.post(this.getContext()+"/ui/throughput/all/open").then(function(e){e&&e.data?"0"===e.data.code?(t.$message.success(e.data.msg),t.watchData()):t.$message.error(e.data.msg):t.$message.error("unknown error")})},closeManagement:function(){var t=this;this.$http.post(this.getContext()+"/ui/throughput/all/close").then(function(e){e&&e.data?"0"===e.data.code?(t.$message.success(e.data.msg),t.watchData()):t.$message.error(e.data.msg):t.$message.error("unknown error")})},watchData:function(){var t=this;this.$http.get(this.getContext()+"/ui/throughput/all/watch").then(function(e){e&&e.data?"0"===e.data.code?t.serviceThroughputList=e.data.data:t.$message.error(e.data.msg):t.$message.error("unknown error")}).catch(function(t){console.log(t)})},throughputTotalSum:function(){var t=0;return this.serviceThroughputList.forEach(function(e){e.dataList.forEach(function(e){t+=parseFloat(e.throughput)})}),t.toFixed(4)},throughputPartSum:function(t){var e=0;return t.forEach(function(t){e+=parseFloat(t.throughput)}),e.toFixed(4)},allHistorySum:function(){for(var t=[],e=[],a=this.serviceThroughputList[0].historyTotalThroughput.dataValues.length,o=0;o<a;o++)t[o]=0;return this.serviceThroughputList.forEach(function(a){e=a.historyTotalThroughput.dataNames,a.historyTotalThroughput.dataValues.forEach(function(e,a){t[a]+=parseFloat(e)})}),{dataNames:e,dataValues:t}}},mounted:function(){var t=this;this.watchData(),setInterval(function(){t.watchData()},5e3)}},c={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"app"}},[a("div",{attrs:{id:"throughput"}},[a("h1",{staticStyle:{"text-align":"center"}},[t._v("各实例吞吐量情况展示")]),t._v(" "),a("el-col",{attrs:{span:12,offset:10}},[a("el-row",[a("el-button",{attrs:{type:"primary",round:""},on:{click:t.openManagement}},[t._v("打开吞吐量监测器")]),t._v(" "),a("el-button",{attrs:{type:"danger",round:""},on:{click:t.closeManagement}},[t._v("关闭吞吐量监测器")])],1)],1),t._v(" "),a("el-col",{attrs:{span:12,offset:6}},[a("el-row",[a("el-col",{attrs:{span:12}},[a("div",{staticStyle:{"max-height":"250px","max-width":"200px"}},[a("throughput-history",{attrs:{history:t.allHistorySum()}})],1)]),t._v(" "),a("el-col",{attrs:{span:12}},[a("div",{staticStyle:{"max-height":"250px","max-width":"200px","margin-top":"40px"}},[a("throughput-guage",{attrs:{throughput:t.throughputTotalSum(),valueName:t.totalValueName}})],1)])],1)],1),t._v(" "),a("el-col",{attrs:{span:12,offset:6}},t._l(t.serviceThroughputList,function(e){return a("el-row",{key:e.host,staticStyle:{"border-top":"1px solid #f0f0f0","padding-top":"5px","margin-top":"10px"}},[a("el-col",{attrs:{span:9,offset:3}},[a("div",{staticStyle:{"margin-top":"15px","min-width":"300px"}},[a("span",[t._v("服务实例地址：")]),t._v(" "),a("el-tag",[t._v(t._s(e.host))])],1)]),t._v(" "),a("el-col",{attrs:{span:11,offset:1}},[a("div",{staticStyle:{"margin-top":"15px","min-width":"300px"}},[a("span",[t._v("吞吐量监测器运行状态：")]),t._v(" "),a("el-tag",{attrs:{type:e.status?"success":"danger"}},[t._v(t._s(e.status?"运行中":"已停止")+"\n            ")])],1)]),t._v(" "),a("el-col",{attrs:{span:12}},[a("div",{staticStyle:{"margin-top":"-30px","max-height":"250px","max-width":"200px"}},[a("throughput-history",{attrs:{history:e.historyTotalThroughput}})],1)]),t._v(" "),a("el-col",{attrs:{span:12}},[a("div",{staticStyle:{"margin-top":"10px","max-height":"250px","max-width":"200px"}},[a("throughput-guage",{attrs:{throughput:t.throughputPartSum(e.dataList),valueName:t.valueName}})],1)]),t._v(" "),a("el-col",[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.dataList}},[a("el-table-column",{attrs:{prop:"itemName",label:"吞吐量名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"throughput",label:"吞吐量"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-tag",{attrs:{type:"success"}},[t._v(t._s(e.row.throughput))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"averageCost",label:"平均处理时间(ms)"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-tag",{attrs:{type:"warning"}},[t._v(t._s(e.row.averageCost))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"lastCost",label:"最后一次处理时间(ms)"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-tag",{attrs:{type:"warning"}},[t._v(t._s(e.row.lastCost))])]}}])})],1)],1)],1)}),1)],1),t._v(" "),a("router-view")],1)},staticRenderFns:[]},l=a("VU/8")(h,c,!1,null,null,null).exports,d=a("zL8q"),p=a.n(d),g=(a("tvR6"),a("/ocq"));o.default.use(g.a);var f=new g.a({mode:"history",routes:[]}),m=a("C/JF"),v=a("fhbW"),y=a("1e6/"),_=a("mtWM"),x=a.n(_),w=(a("pw1w"),a("HSQo")),b=a.n(w),T=a("Gu7T"),$=a.n(T),O=a("Icdr"),S=a.n(O),L=a("O4Lo"),M=a.n(L),A=a("472O"),N=["legendselectchanged","legendselected","legendunselected","legendscroll","datazoom","datarangeselected","timelinechanged","timelineplaychanged","restore","dataviewchanged","magictypechanged","geoselectchanged","geoselected","geounselected","pieselectchanged","pieselected","pieunselected","mapselectchanged","mapselected","mapunselected","axisareaselected","focusnodeadjacency","unfocusnodeadjacency","brush","brushselected","rendered","finished","click","dblclick","mouseover","mouseout","mousemove","mousedown","mouseup","globalout","contextmenu"],C={props:{options:Object,theme:[String,Object],initOptions:Object,group:String,autoresize:Boolean,watchShallow:Boolean,manualUpdate:Boolean},data:function(){return{lastArea:0}},watch:{group:function(t){this.chart.group=t}},methods:{mergeOptions:function(t,e,a){this.manualUpdate&&(this.manualOptions=t),this.chart?this.delegateMethod("setOption",t,e,a):this.init()},appendData:function(t){this.delegateMethod("appendData",t)},resize:function(t){this.delegateMethod("resize",t)},dispatchAction:function(t){this.delegateMethod("dispatchAction",t)},convertToPixel:function(t,e){return this.delegateMethod("convertToPixel",t,e)},convertFromPixel:function(t,e){return this.delegateMethod("convertFromPixel",t,e)},containPixel:function(t,e){return this.delegateMethod("containPixel",t,e)},showLoading:function(t,e){this.delegateMethod("showLoading",t,e)},hideLoading:function(){this.delegateMethod("hideLoading")},getDataURL:function(t){return this.delegateMethod("getDataURL",t)},getConnectedDataURL:function(t){return this.delegateMethod("getConnectedDataURL",t)},clear:function(){this.delegateMethod("clear")},dispose:function(){this.delegateMethod("dispose")},delegateMethod:function(t){var e;this.chart||this.init();for(var a=arguments.length,o=Array(a>1?a-1:0),n=1;n<a;n++)o[n-1]=arguments[n];return(e=this.chart)[t].apply(e,$()(o))},delegateGet:function(t,e){return this.chart||this.init(),this.chart[e]()},getArea:function(){return this.$el.offsetWidth*this.$el.offsetHeight},init:function(){var t=this;if(!this.chart){var e=S.a.init(this.$el,this.theme,this.initOptions);this.group&&(e.group=this.group),e.setOption(this.manualOptions||this.options||{},!0),N.forEach(function(a){e.on(a,function(e){t.$emit(a,e)})}),this.autoresize&&(this.lastArea=this.getArea(),this.__resizeHandler=M()(function(){0===t.lastArea?(t.mergeOptions({},!0),t.resize(),t.mergeOptions(t.options||t.manualOptions||{},!0)):t.resize(),t.lastArea=t.getArea()},100,{leading:!0}),Object(A.a)(this.$el,this.__resizeHandler)),b()(this,{width:{configurable:!0,get:function(){return t.delegateGet("width","getWidth")}},height:{configurable:!0,get:function(){return t.delegateGet("height","getHeight")}},isDisposed:{configurable:!0,get:function(){return!!t.delegateGet("isDisposed","isDisposed")}},computedOptions:{configurable:!0,get:function(){return t.delegateGet("computedOptions","getOption")}}}),this.chart=e}},destroy:function(){this.autoresize&&Object(A.b)(this.$el,this.__resizeHandler),this.dispose(),this.chart=null},refresh:function(){this.chart&&(this.destroy(),this.init())}},created:function(){var t=this;this.manualUpdate||this.$watch("options",function(e,a){!t.chart&&e?t.init():t.chart.setOption(e,e!==a)},{deep:!this.watchShallow});["theme","initOptions","autoresize","manualUpdate","watchShallow"].forEach(function(e){t.$watch(e,function(){t.refresh()},{deep:!0})})},mounted:function(){this.options&&this.init()},activated:function(){this.autoresize&&this.chart&&this.chart.resize()},beforeDestroy:function(){this.chart&&this.destroy()},connect:function(t){"string"!=typeof t&&(t=t.map(function(t){return t.chart})),S.a.connect(t)},disconnect:function(t){S.a.disConnect(t)},registerMap:function(t,e,a){S.a.registerMap(t,e,a)},registerTheme:function(t,e){S.a.registerTheme(t,e)},graphic:S.a.graphic},D={render:function(){var t=this.$createElement;return(this._self._c||t)("div",{staticClass:"echarts"})},staticRenderFns:[]};var z=a("VU/8")(C,D,!1,function(t){a("db+/")},null,null).exports;a("tcAE"),a("64cm");o.default.prototype.$http=x.a,m.c.add(v.a),o.default.component("font-awesome-icon",y.a),o.default.use(p.a),o.default.component("v-chart",z),o.default.config.productionTip=!1,new o.default({el:"#app",router:f,components:{App:l},template:"<App/>"})},"db+/":function(t,e){},fP8A:function(t,e){},pw1w:function(t,e){},tvR6:function(t,e){}},["NHnr"]);