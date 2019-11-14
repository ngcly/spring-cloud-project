<template>
  <div class="app-container">
    <iframe width="100%" frameborder="0" id="bdIframe" scrolling="auto" :src="url"></iframe>
  </div>
</template>

<script>
    import {admin} from '@/api/constant'
    export default {
        name: "admin",
        data() {
            return {
                url: admin
            }
        },
        mounted(){
            /**
             * iframe-宽高自适应显示
             */
            const oIframe = document.getElementById('bdIframe');
            const deviceHeight = document.documentElement.clientHeight;
            oIframe.style.height = (Number(deviceHeight)-120) + 'px';
        }
    }
</script>

<style scoped>
  iframe {
    min-height: 600px;
  }
</style>
