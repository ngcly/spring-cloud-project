<template>
  <div class="app-container">
    <iframe width="100%" frameborder="0" scrolling="no" :src="url"></iframe>
  </div>
</template>

<script>
    import {nacos} from '@/api/constant'
    export default {
        name: "nacos",
        data() {
            return {
                url: nacos
            }
        }
    }
</script>

<style scoped>
  iframe {
    min-height: 600px;
  }
</style>
