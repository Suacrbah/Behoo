<template>
  <div id="login-form">
    <form @submit.prevent="handleLogin()">
      <label>Username</label>
      <input v-model="user.username" type="text" />
      <label>Password</label>
      <input v-model="user.password" type="password" />
      <button @click="handleLogin()">Login</button>
      <button @click="handleHello()">Hello</button>
    </form>
    <p>Response: {{ response }}</p>
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

<style scoped>
  form {
    margin-bottom: 2rem;
  }
</style>