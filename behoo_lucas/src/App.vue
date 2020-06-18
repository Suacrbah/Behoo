<template>
  <div id="app" class="small-container">
    <h1>Welcome</h1>
    <LoginForm/>
  </div>
</template>

<script>
  import LoginForm from './components/LoginForm'

  export default {
    name: 'App',
    components: {
      LoginForm,
    },
    data() {
      return {
        employee: {
          name: '',
          email: '',
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
