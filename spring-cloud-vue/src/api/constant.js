import request from '@/utils/request'

// nacos
export const nacos = 'http://localhost:8848/nacos'

// Spring Boot Admin
export const admin = 'http://localhost:8005/'

// Swagger2
export const swagger = 'http://localhost:8002/swagger-ui.html'

// sentinel
export const sentinel = 'http://localhost:8088'

// druid 监控
export const druid = 'http://localhost:8001/druid/index.html'

export function hi() {
  return request({
    url: '/user/hey',
    method: 'get'
  })
}
