import axios from 'axios';
import qs from 'qs';
import localStorage from '@/utils/localStorage';

const httpAjax = axios.create({
    baseURL: '/api',
    // `withCredentials` 表示跨域请求时是否需要使用凭证
    withCredentials: false,
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
    },
    timeout: 30000,
    transformRequest: [function (data) {
        let req = qs.stringify(data, {arrayFormat: 'repeat'});
        return req;
    }]
});

// request 拦截器
httpAjax.interceptors.request.use(config => {
        // config.headers.token = sessionStorage.token;
        config.data = {
            ...config.data,
            JSESSIONID: localStorage.get("JSESSIONID"),
        }
        return config;
    },
    error => Promise.reject(error)
);

// respone 拦截器
httpAjax.interceptors.response.use(
    (response) => {
        const res = response.data;
        // if (res.status !== 1) {
        //     return Promise.reject(res);
        // }
        return Promise.resolve(res);
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default httpAjax;
