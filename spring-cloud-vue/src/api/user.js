import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/ah/oauth/token',
    method: 'post',
    params: data
  })
}

export function getInfo() {
  return request({
    url: '/ah/user/info',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
