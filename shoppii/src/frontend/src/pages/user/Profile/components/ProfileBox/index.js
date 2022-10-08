import React from 'react'
import { Container, Row, Col } from 'react-bootstrap'
import EditProfileForm from '../EditProfileForm'
import ChangPassword from '../ChangePass'
import OrderHistoryItem from '../OrderHistoryItem'
import './index.css'
import {editPro, changePass, orderHistory, addressList} from '../ProfileSidebar'

function ProfileBox({content}) {
    console.log(content)
    const renderContent = () => {
        switch(content){
            case changePass:
                return <ChangPassword/>
            case orderHistory:
                return <OrderHistoryItem/>
            default: return <EditProfileForm/>
        }
    }

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
                            {renderContent()}
                        </div>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}

export default ProfileBox
