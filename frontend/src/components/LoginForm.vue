<template>
    <div class="vue-tempalte">
        <form @submit.prevent="handleLogin()">
            <h3>Log In</h3>

            <div class="form-group">
                <label>Username</label>
                <input type="text" class="form-control form-control-lg"/>
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control form-control-lg" />
            </div>

            <button type="submit" class="btn btn-dark btn-lg btn-block" @click="handleLogin()">Log In</button>

            <p class="forgot-password text-right">
                Not a member? 
                <router-link :to="{name: 'login'}">sign up</router-link>
            </p>
        </form>
    </div>
</template>

<script>
  import api from "./backend-api";

  export default {
    name: 'Login-Form',
    data() {
      return {
        response: 1,
        errors: [],
        user: {
          username: '',
          password: '',
        },
      };
    },
    methods: {
      handleLogin() {
        api.auth(this.user.username, this.user.password)
        .then(response => {
          this.response = response.data['statu'];
          if (this.response == 0) {
            this.$router.push('/Editor', () => {});
          }
        })
        .catch(e => {
          this.errors.push(e)
        });
      },
    }
  }
</script>