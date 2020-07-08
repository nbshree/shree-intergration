import appConst from '../appConst';
import httpAjax from '@/utils/httpAjax';

const user = {
    namespaced: 'user',
    state: {
        sessionId: '',
        userId: '',
        userName: '',
        nickName: '',
        roles: [],
        avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80',
        introduction: ''
    },
    mutations: {
        [appConst.user.motaions.SET_SESSION]: (state, sessionId) => {
            state.sessionId = sessionId;
        },
        [appConst.user.motaions.SET_USER_INFO]: (state, userInfo) => {
            state.sessionId = userInfo.sessionId;
            state.userId = userInfo.id;
            state.userName = userInfo.userName;
            state.nickName = userInfo.nickName;
            // state.roles = userInfo.roles;
        },
        [appConst.user.motaions.SET_AVATAR]: (state, avatar) => {
            state.avatar = avatar;
        },
        [appConst.user.motaions.SET_INTRODUCTION]: (state, introduction) => {
            state.introduction = introduction;
        }
    },
    actions: {
        login({commit}, {username, password}) {
            return new Promise((resolve, reject) => {
                httpAjax.get('/open/login', {
                    params: {
                        userName: username,
                        userPwd: password
                    }
                }).then(response => {
                    if (response.status === 1) {
                        commit(appConst.user.motaions.SET_USER_INFO, response.data);
                        // loginAuth.setSession(JSON.stringify(response.data));
                        // loginAuth.setSessionId(response.data.sessionId);
                    }
                    resolve(response)
                }).catch(error => {
                    reject(error);
                });
            });
        },
        logout({commit, state}) {
            return new Promise((resolve, reject) => {
                httpAjax.get('/open/login').then(response => {
                    if (response.data) {
                        commit(appConst.user.motaions.SET_USER_INFO, {
                            sessionId: '',
                            userId: '',
                            userName: '',
                            nickName: '',
                            roles: [],
                            avatar: '',
                            introduction: ''
                        });
                        // loginAuth.removeSessionId();
                        // loginAuth.removeSession();
                        resolve();

                    }
                    resolve(response);
                }).catch(error => {
                    reject(error);
                });
            });
        }
    }
};

export default user;
