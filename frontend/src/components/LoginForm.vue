<template>
    <div class="vue-tempalte">
        <form @submit.prevent="handleLogin()">
            <h3>Log In</h3>

            <div class="form-group">
                <label>Username</label>
                <input v-model="user.username" type="text" class="form-control form-control-lg"/>
            </div>

            <div class="form-group">
                <label>Password</label>
                <input v-model="user.password" type="password" class="form-control form-control-lg" />
            </div>

            <button type="submit" class="btn btn-dark btn-lg btn-block" >Log In</button>

            <p class="forgot-password text-right">
                Not a member? 
                <router-link :to="{name: 'login'}">sign up</router-link>
            </p>
            <p class="forgot-password text-right">
              {{ this.message }}
            </p>
        </form>
    </div>
</template>

<script>
  // import api from "./backend-api";
  import axios from 'axios'

  export default {
    name: 'Login-Form',
    data() {
      return {
        message: '',
        response: {},
        result: 0,
        authorization: '',
        errors: [],
        user: {
          username: '',
          password: '',
        },
      };
    },
    methods: {
      handleLogin() {
        // api.auth(this.user.username, this.user.password)
        // .then(response => {
        //   this.response = response.data['statu'];
        //   if (this.response == 0) {
        //     this.$router.push('/Editor', () => {});
        //   }
        // })
        // .catch(e => {
        //   this.errors.push(e)
        // });
        axios.post('http://192.168.43.233:8889/login', {
          username: this.user.username,
          hashedPassword: this.user.password
        })
        .then(response => {
          this.response = response;
          this.result = this.response.data['code'];
          if (this.result == 200) {
            this.authorization = this.response.headers['authorization'];
            this.$router.push('/editor', () => {});
          } else {
            this.message = 'Login Failed!!!';
          }
        })
        .catch(e => {
          this.errors.push(e);
        })
      },
    }
  }
</script>