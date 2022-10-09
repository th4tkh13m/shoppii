import React from 'react'
import { Container, Col, Row } from 'react-bootstrap'
import './index.css'
import {DeleteOutline, BorderColorOutlined} from '@mui/icons-material';

function AddressItem() {
    return (
        <div className="address">
            <Container fluid="md" sx={{ width: '100%', height: 'auto'}}>
                <Row>
                    <Col md={10}>
                        <p>
                            <h3>Tên</h3>
                        </p>
                        <p className="fs-4">
                            <span className="fs-4" style={{ color: 'gray' }}>
                                Địa chỉ:{' '}
                            </span>
                            1x/45x đường XX, phường XX, Quận XX, Thành phố XX
                        </p>
                        <p>
                            <span className="fs-4" style={{ color: 'gray' }}>
                                Số điện thoại:{' '}
                            </span>
                            0549855678
                        </p>
                    </Col>
                    <Col md={2}>
                        <p className="d-flex justify-content-end align-content-center">
                            <DeleteOutline className="mt-1" sx={{ fontSize: '18px', color: 'red'}}/>
                            <span className="fs-5 mt-2" style={{color: 'red'}}>Xóa</span>
                        </p>
                        <p className="d-flex justify-content-end align-content-center">
                            <BorderColorOutlined className="mt-1" sx={{ fontSize: '18px', color: 'success.main'}}/>
                            <span className="fs-5 mt-2" style={{color: 'green'}}>Chỉnh sửa</span>
                        </p>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}

export default AddressItem
