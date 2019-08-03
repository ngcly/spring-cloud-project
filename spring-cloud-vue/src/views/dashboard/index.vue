<template>
  <div class="dashboard-container">
    <div class="dashboard-text">name: {{ username }}</div>
    <div>hello: {{ test }}</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { hi } from '@/api/constant'
export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'username'
    ])
  },
    data(){
      return {
          test: ''
      }
    },
    mounted() {
        return new Promise((resolve, reject) => {
            hi().then(response => {
                this.test = response.data;
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
