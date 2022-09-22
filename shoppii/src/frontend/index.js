const form = document.querySelector('#form')

form.addEventListener('submit', async e => {
  e.preventDefault()
  console.dir(form)
  const formData = new FormData(form)
  formData.append('name', 'test')
  for (const [key, value] of formData) {
    console.log(`${key}: ${value}\n`)
  }

  $.ajax({
    url: 'http://localhost:8080/shoppii/uploadservlet',
    type: 'POST',
    processData: false,
    contentType: false,
    enctype: 'multipart/form-data',
    data: formData,
    dataType: 'json',
    success: function (response) {
      // console.log($.parseJSON(response))
      console.log(response)
    },
    error: function (error) {
      console.log(error)
    },
  })
})

const handleChange = e => {
  console.log(e.files)
}
