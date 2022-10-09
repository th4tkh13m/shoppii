import React from 'react'
import { Container, Row, Col } from 'react-bootstrap'
import EditProfileForm from '../EditProfileForm'
import ChangPassword from '../ChangePass'
import OrderHistory from '../OrderHistory'
import './index.css'
import {
    editPro,
    changePass,
    orderHistory,
    addressList,
} from '../ProfileSidebar'

const headerList = [
    {
        action: editPro,
        title: 'Hồ Sơ Của Tôi',
        comment: 'Quản lý thông tin hồ sơ để bảo mật tài khoản',
    },
    {
        action: addressList,
        title: 'Địa chỉ của tôi',
        comment: 'Địa chỉ nhận hàng',
    },
    {
        action: changePass,
        title: 'Đổi Mật Khẩu',
        comment:
            'Để bảo mật tài khoản, vui lòng không chia sẻ mật khẩu cho người khác',
    },
    {
        action: orderHistory,
        title: 'Lịch sử mua hàng',
        comment: 'Quản lý những đơn hàng đã được giao',
    },
]

function ProfileBox({ content }) {
    const renderContent = () => {
        switch (content) {
            case changePass:
                return <ChangPassword />
            case orderHistory:
                return <OrderHistory />
            default:
                return <EditProfileForm />
        }
    }

    return (
        <div className="profile-box">
            <Container fluid="md">
                <Row className="box-header">
                    {headerList.map(header => {
                        if (header.action === content) {
                            return (
                                <Col md={12}>
                                    <h2>{header.title}</h2>
                                    <div>{header.comment}</div>
                                </Col>
                            )
                        }
                    })}
                </Row>
                <Row>
                    <Col md={12} className="d-flex justify-content-center">
                        <div className="box-contain">{renderContent()}</div>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}

export default ProfileBox
