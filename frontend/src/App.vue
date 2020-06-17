<template>
  <div id="app" class="small-container">
    <h1>Welcome</h1>
    <employee-form />
  </div>
</template>

<script>
import EmployeeForm from './components/EmployeeForm.vue'

export default {
  name: 'App',
  components: {
    EmployeeForm,
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
