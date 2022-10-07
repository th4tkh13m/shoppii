import React from 'react'
import './index.css'
import {
    TextField,
    Radio,
    RadioGroup,
    FormControlLabel,
    FormControl,
    FormLabel,
    InputLabel,
    MenuItem,
    Select,
    Button,
    Avatar,
} from '@mui/material'

function EditProfileForm() {
    const [date, setDate] = React.useState('')
    const [month, setMonth] = React.useState('')
    const [year, setYear] = React.useState('')

    const handleChange = (event, item) => {
        switch (item) {
            case 'date':
                setDate(event.target.value)
                break
            case 'month':
                setMonth(event.target.value)
                break
            case 'year':
                setYear(event.target.value)
                break
            default:
                return 'Error'
        }
    }

    const createArray = item => {
        let array = []
        switch (item) {
            case 'date':
                for (let i = 0; i < 31; i++) {
                    array = [...array, i + 1]
                }
                return array
            case 'month':
                for (let i = 0; i < 12; i++) {
                    array = [...array, i + 1]
                }
                return array
            case 'year':
                for (let i = 1980; i < 2022; i++) {
                    array = [...array, i + 1]
                }
                return array
            default:
                return array
        }
    }

    return (
        <div className="box-contain">
            <div className="container">
                <div className="row">
                    <div className="col-xs-12 col-sm-8 profile-content d-block">
                        <TextField
                            required
                            id="outlined-required"
                            label="Tên"
                            defaultValue=""
                            size="small"
                            margin="normal"
                            fullWidth
                        />
                        <TextField
                            required
                            id="outlined-required"
                            label="Email"
                            defaultValue=""
                            size="small"
                            margin="normal"
                            fullWidth
                        />
                        <TextField
                            required
                            id="outlined-required"
                            label="Số điện thoại"
                            defaultValue=""
                            size="small"
                            margin="normal"
                            fullWidth
                        />
                        <FormControl>
                            <FormLabel id="demo-row-radio-buttons-group-label">
                                Giới tính
                            </FormLabel>
                            <RadioGroup
                                row
                                aria-labelledby="demo-row-radio-buttons-group-label"
                                name="row-radio-buttons-group"
                            >
                                <FormControlLabel
                                    value="Nam"
                                    control={<Radio />}
                                    label="Nam"
                                />
                                <FormControlLabel
                                    value="Nữ"
                                    control={<Radio />}
                                    label="Nữ"
                                />
                                <FormControlLabel
                                    value="Khác"
                                    control={<Radio />}
                                    label="Khác"
                                />
                            </RadioGroup>
                        </FormControl>{' '}
                        <br></br>
                        <FormLabel id="demo-row-radio-buttons-group-label">
                            Ngày sinh
                        </FormLabel>{' '}
                        <br></br>
                        <FormControl sx={{ m: 1, minWidth: 70 }} size="small">
                            <InputLabel id="demo-select-small">Ngày</InputLabel>
                            <Select
                                labelId="demo-select-small"
                                id="demo-select-small"
                                value={date}
                                onChange={event => handleChange(event, 'date')}
                            >
                                {createArray('date').map(index => (
                                    <MenuItem value={index}>{index}</MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                        <FormControl sx={{ m: 1, minWidth: 70 }} size="small">
                            <InputLabel id="demo-select-small">
                                Tháng
                            </InputLabel>
                            <Select
                                labelId="demo-select-small"
                                id="demo-select-small"
                                value={month}
                                onChange={event => handleChange(event, 'month')}
                            >
                                {createArray('month').map(index => (
                                    <MenuItem value={index}>{index}</MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                        <FormControl sx={{ m: 1, minWidth: 100 }} size="small">
                            <InputLabel id="demo-select-small">Năm</InputLabel>
                            <Select
                                labelId="demo-select-small"
                                id="demo-select-small"
                                value={year}
                                onChange={event => handleChange(event, 'year')}
                            >
                                {createArray('year').map(index => (
                                    <MenuItem value={index}>{index}</MenuItem>
                                ))}
                            </Select>
                        </FormControl>{' '}
                        <br></br>
                        <div className="save-btn p-3 justify-content-center d-flex">
                            <Button variant="contained" className="fs-6 w-25">
                                Lưu
                            </Button>
                        </div>
                    </div>
                    <div className="col-xs-12 col-sm-4 profile-avatar pt-5 d-block">
                        <Avatar
                            alt="Remy Sharp"
                            src="/static/images/avatar/1.jpg"
                            sx={{ width: 125, height: 125 }}
                            className="mx-auto my-0"
                        />
                        <div className='selec-img my-4'>
                            <Button variant="outlined" className="fs-6 w-35">
                                Chọn ảnh
                            </Button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default EditProfileForm
