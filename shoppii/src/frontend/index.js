const form = document.querySelector('#form')
const getPhotoBtn = document.querySelector('#getPhoto')
const root = document.querySelector('#root')

form.addEventListener('submit', async e => {
  e.preventDefault()
  console.dir(form)
  const formData = new FormData(form)
  formData.append('name', 'test')
  for (const [key, value] of formData) {
    console.log(`${key}: ${value}\n`)
  }

  //   $.ajax({
  //     url: 'http://localhost:8080/shoppii/uploadservlet',
  //     type: 'POST',
  //     processData: false,
  //     contentType: false,
  //     enctype: 'multipart/form-data',
  //     data: formData,
  //     dataType: 'json',
  //     success: function (response) {
  //       // console.log($.parseJSON(response))
  //       console.log(response)
  //     },
  //     error: function (error) {
  //       console.log(error)
  //     },
  //   })

  const response = await fetch('http://localhost:8080/shoppii/uploadservlet', {
    method: 'POST',
    body: formData,
  })

  console.log(response)
  console.log(await response.json())
})

const handleChange = e => {
  console.log(e.files)
}

getPhotoBtn.addEventListener('click', async () => {
  const AWS_URI =
    'https://photo-shoppii.s3.ap-southeast-1.amazonaws.com/dao-test-2/'
  const response = await fetch('http://localhost:8080/shoppii/uploadservlet')
  const data = await response.json()
  console.log(data)
  const { title, listPhotos } = data

  const element = listPhotos
    .map(photo => {
      return `<img src="${
        AWS_URI + photo
      }" alt="${title}" width="300px" height="400px"/>`
    })
    .join(' ')

  root.innerHTML = element
})
