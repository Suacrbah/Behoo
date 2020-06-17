<template>
  <div id="login-form">
    <form @submit.prevent="handleSubmit">
      <label>Username</label>
      <input v-model="user.username" type="text" />
      <label>Password</label>
      <input v-model="user.password" type="text" />
      <button @click="handleLogin()">Login</button>
    </form>
  </div>
</template>

<script>
  import api from "./backend-api";

  export default {
    name: 'Login-Form',
    data() {
      return {
        response: 0,
        errors: [],
        user: {
          username: '',
          password: '',
        },
      }
    },
    methods: {
      handleLogin() {
        api.auth(this.user.username, this.user.password)
        .then(response => {
          this.response = response.data;
        })
        .catch(e => {
          this.errors.push(e)
        });
      }
    }
  }
</script>

<style scoped>
  form {
    margin-bottom: 2rem;
  }
</style>