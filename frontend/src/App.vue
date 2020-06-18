<template>
  <div id="app" class="small-container">
    <Login-form />
    <Editor />
  </div>
</template>

<script>
import LoginForm from './components/LoginForm.vue'
import Editor from './components/Editor.vue'

export default {
  name: 'App',
  components: {
    LoginForm,
    Editor,
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
