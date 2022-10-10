const handleChange = (e, setValue) => {
    const { name, value } = e.target
    setValue(prevState => ({
        ...prevState,
        [name]: value,
    }))
}

export { handleChange }
