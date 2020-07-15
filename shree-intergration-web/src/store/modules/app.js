import appConst from '../appConst';

export default {
    namespaced: 'app',
    state: {
        sidebar: {
            sliderState: false
        }
    },
    mutations: {
        [appConst.app.motaions.SET_SLIDERSTATE]: (state) => {
            state.sidebar.sliderState = !state.sidebar.sliderState;
        }
    },
    actions: {
        setSliderState({commit}) {
            commit(appConst.app.motaions.SET_SLIDERSTATE);
        },
    }
}
