<template>
  <div class="page-container">
    {{ msg }}
    <label>
      <input v-model="username"/>
    </label>
    <button @click="login">登陆</button>
    <button @click="getInfo">获取用户列表</button>
  </div>
</template>

<script>
  import httpAjax from '@/utils/httpAjax';

  export default {
    methods: {
      getInfo(){
        httpAjax.get('/system/user/info/load',{
          params:{
            id:'5447188719000879104'
          }
        }).then((response) => {
          console.log(response);
        },(reject)=>{
          console.log(reject)
        });
      },
      login(){
        httpAjax.get('/open/login',{
          params:{
            userName:'admin',
            userPwd:'qwe123'
          }
        }).then((response) => {
          console.log(response);
        },(reject)=>{
          console.log(reject)
        });
      }
    },
    data() {
      return {
        msg: 'userList',
        username:"shree"
      };
    }
  };
</script>
