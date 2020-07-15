import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

import user from './modules/user';
import app from './modules/app';
import getters from './getters';

const store = new Vuex.Store({
    modules: {
        user,
        app
    },
    getters,
    strict: false
});

export default store;
