import Vue from 'vue';
import App from './App.vue';
import Antd from 'ant-design-vue';
import router from '@/router';
import less from 'less';

import "ant-design-vue/dist/antd.css";
import '@/assets/styl/index';

Vue.use(less);
Vue.use(Antd);
Vue.config.productionTip = false;

new Vue({
    router,
    render: h => h(App),
}).$mount('#app')
