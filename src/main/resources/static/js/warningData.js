const deleteDish = (ids) => {
    return axios({
        url: '/warning',
        method: 'delete',
        params: { ids }
    })
}