import Vue from 'vue';
import App from './App.vue';
import Antd from 'ant-design-vue';
import router from '@/router';
import store from './store'
// import less from 'less';

import "ant-design-vue/dist/antd.css";
import '@/assets/styl/index';
// 引入vue-amap
import VueAMap from 'vue-amap';
Vue.use(VueAMap);
// 初始化vue-amap
VueAMap.initAMapApiLoader({
    // 高德的key
    key: 'e777a81b8b3237a9f3f739cf88b03b60',
    // 插件集合
    plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor'],
    // 高德 sdk 版本，默认为 1.4.4
    v: '1.4.4'
});
// Vue.use(less);
Vue.use(Antd);
Vue.config.productionTip = false;

new Vue({
    store,
    router,
    render: h => h(App),
}).$mount('#app')
