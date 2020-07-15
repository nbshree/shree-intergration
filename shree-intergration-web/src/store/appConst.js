const appConst = {
    app: {
        cookies: {
            firstLogin: 'shree-firstLogin',
            language: 'shree-language',
            sliderState: 'shree-sliderState',
            lock: 'shree-lock'
        },
        localStorage: {
            sliderState: 'shree-sliderState',
            lock: 'shree-lockState'
        },
        motaions: {
            SET_LANGUAGE: 'SET_LANGUAGE',
            SET_FIRSTLOGIN: 'SET_FIRSTLOGIN',
            SET_SLIDERSTATE: 'SET_SLIDERSTATE',
            SET_LOCK_STATE: 'SET_LOCK_STATE'
        }
    },
    user: {
        cookies: {
            userInfo: 'shree-userInfo'
        },
        motaions: {
            SET_SESSION_ID: 'SET_SESSION_ID',
            SET_USER_INFO: 'SET_USER_INFO',
            SET_PERMISSION: 'SET_PERMISSION',
            SET_AVATAR: 'SET_AVATAR',
            SET_INTRODUCTION: 'SET_INTRODUCTION'
        }
    }
};

export default appConst;
