import React from 'react'
import { Container, Row, Col } from 'react-bootstrap'
import EditProfileForm from '../EditProfileForm'
import ChangPassword from '../ChangePass'
import OrderHistoryItem from '../OrderHistoryItem'
import './index.css'

function ProfileBox() {
    return (
        <div className="profile-box">
            <Container fluid="md">
                <Row className="box-header">
                    <Col md={12}>
                        <h2>Đổi mật khẩu</h2>
                        <div>
                            Để bảo mật tài khoản, vui lòng không chia sẻ mật
                            khẩu cho người khác
                        </div>
                    </Col>
                </Row>
                <Row>
                    <Col md={12} className="d-flex justify-content-center">
                        <div className='box-contain'>
                            <OrderHistoryItem/>
                            <OrderHistoryItem/>
                        </div>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}

export default ProfileBox
