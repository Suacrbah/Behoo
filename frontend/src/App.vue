<template>
  <div id="app" class="small-container">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      message: 'Hello World',
    }
  },
}
</script>

<style scoped>
  button {
    background: #009435;
    border: 1px solid #009435;
  }

  .small-container {
    max-width: 680px;
  }
</style>
