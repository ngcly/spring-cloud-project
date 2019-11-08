
<template>
  <div class="app-container">
    <iframe width="100%" frameborder="0" scrolling="auto" :src="url"></iframe>
  </div>
</template>

<script>
    import {sentinel} from '@/api/constant'
    export default {
        name: "sentinel",
        data() {
            return {
                url: sentinel
            }
        }
    }
</script>

<style scoped>
  iframe {
    min-height: 600px;
  }
</style>
