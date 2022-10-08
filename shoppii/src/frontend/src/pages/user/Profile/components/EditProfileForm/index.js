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
import { Row, Container, Col } from 'react-bootstrap'

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
        <Container fluid="md">
            <Row>
                <Col md={8} className="profile-content">
                    <Container>
                        <Row>
                            <TextField
                                required
                                id="outlined-required"
                                label="Tên"
                                defaultValue=""
                                size="small"
                                margin="normal"
                                fullWidth
                            />
                        </Row>
                        <Row>
                            <TextField
                                required
                                id="outlined-required"
                                label="Email"
                                defaultValue=""
                                size="small"
                                margin="normal"
                                fullWidth
                            />
                        </Row>
                        <Row>
                            <TextField
                                required
                                id="outlined-required"
                                label="Số điện thoại"
                                defaultValue=""
                                size="small"
                                margin="normal"
                                fullWidth
                            />
                        </Row>
                        <Row>
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
                                        value="male"
                                        control={<Radio size="large" />}
                                        label="Nam"
                                    />
                                    <FormControlLabel
                                        value="female"
                                        control={<Radio size="large" />}
                                        label="Nữ"
                                    />
                                    <FormControlLabel
                                        value="other"
                                        control={<Radio size="large" />}
                                        label="Khác"
                                    />
                                </RadioGroup>
                            </FormControl>{' '}
                        </Row>
                        <Row>
                            <FormLabel id="demo-row-radio-buttons-group-label">
                                Ngày sinh
                            </FormLabel>{' '}
                            <br></br>
                            <Col md={2}>
                                <FormControl
                                    sx={{ m: 1, minWidth: 70 }}
                                    size="small"
                                >
                                    <InputLabel id="demo-select-small">
                                        Ngày
                                    </InputLabel>
                                    <Select
                                        labelId="demo-select-small"
                                        id="demo-select-small"
                                        value={date}
                                        onChange={event =>
                                            handleChange(event, 'date')
                                        }
                                    >
                                        {createArray('date').map(index => (
                                            <MenuItem value={index}>
                                                {index}
                                            </MenuItem>
                                        ))}
                                    </Select>
                                </FormControl>
                            </Col>
                            <Col md={2}>
                                <FormControl
                                    sx={{ m: 1, minWidth: 70 }}
                                    size="small"
                                >
                                    <InputLabel id="demo-select-small">
                                        Tháng
                                    </InputLabel>
                                    <Select
                                        labelId="demo-select-small"
                                        id="demo-select-small"
                                        value={month}
                                        onChange={event =>
                                            handleChange(event, 'month')
                                        }
                                    >
                                        {createArray('month').map(index => (
                                            <MenuItem value={index}>
                                                {index}
                                            </MenuItem>
                                        ))}
                                    </Select>
                                </FormControl>
                            </Col>
                            <Col md={4}>
                                <FormControl
                                    sx={{ m: 1, minWidth: 100 }}
                                    size="small"
                                >
                                    <InputLabel id="demo-select-small">
                                        Năm
                                    </InputLabel>
                                    <Select
                                        labelId="demo-select-small"
                                        id="demo-select-small"
                                        value={year}
                                        onChange={event =>
                                            handleChange(event, 'year')
                                        }
                                    >
                                        {createArray('year').map(index => (
                                            <MenuItem value={index}>
                                                {index}
                                            </MenuItem>
                                        ))}
                                    </Select>
                                </FormControl>{' '}
                            </Col>
                        </Row>
                        <Row className="d-flex justify-content-center pt-2">
                            <Button variant="contained" className="fs-6 w-25">
                                Lưu
                            </Button>
                        </Row>
                    </Container>
                </Col>
                <Col md={4} className="profile-avatar pt-5 d-block">
                    <Container>
                        <Row>
                            <Avatar
                                alt="Remy Sharp"
                                src="/static/images/avatar/1.jpg"
                                sx={{ width: 125, height: 125 }}
                                className="mx-auto my-0"
                            />
                        </Row>
                        <Row className="d-flex justify-content-center">
                            <Button
                                variant="outlined"
                                className="fs-6 w-50 mt-3"
                                size="medium"
                            >
                                Chọn ảnh
                            </Button>
                        </Row>
                    </Container>
                </Col>
            </Row>
        </Container>
    )
}

export default EditProfileForm
