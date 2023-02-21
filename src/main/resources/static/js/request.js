const getDevicePage = (params) => {
    return $axios({
        url: '/device/page',
        method: 'get',
        params
    })
}