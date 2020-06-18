<template>
  <div id="app" class="small-container">
    <h1>Welcome</h1>
    <Login-form />
  </div>
</template>

<script>
import LoginForm from './components/LoginForm.vue'

export default {
  name: 'App',
  components: {
    LoginForm,
  },
  data() {
    return {
      user: {
        username: '',
        password: '',
      },
    }
  },
}
</script>

<style>
  button {
    background: #009435;
    border: 1px solid #009435;
  }

  .small-container {
    max-width: 680px;
  }
</style>
