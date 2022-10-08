import React from 'react'
import { Container, Row, Col } from 'react-bootstrap'
import './index.css'
import { TextField, Button } from '@mui/material'

function ChangPassword() {
    return (
        <Container fluid="md">
            <Row>
                <Col md={12} className="d-flex justify-content-center">
                    <TextField
                        required
                        id="outlined-required"
                        label="Mật khẩu hiện tại"
                        defaultValue=""
                        size="normal"
                        margin="normal"
                        className="w-50"
                    />
                </Col>
            </Row>
            <Row>
                <Col md={12} className="d-flex justify-content-center">
                    <TextField
                        required
                        id="outlined-required"
                        label="Mật khẩu mới"
                        defaultValue=""
                        size="normal"
                        margin="normal"
                        className="w-50"
                    />
                </Col>
            </Row>
            <Row>
                <Col md={12} className="d-flex justify-content-center">
                    <TextField
                        required
                        id="outlined-required"
                        label="Xác nhận mật khẩu"
                        defaultValue=""
                        size="normal"
                        margin="normal"
                        className="w-50"
                    />
                </Col>
            </Row>
            <Row className="d-flex justify-content-center mt-4">
                <Button variant="contained" className="fs-6 w-25">
                    Xác nhận
                </Button>
            </Row>
        </Container>
    )
}

export default ChangPassword
