import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

import user from './modules/user';
import getters from './getters';

const store = new Vuex.Store({
    modules: {
        user
    },
    getters,
    strict: false
});

export default store;
