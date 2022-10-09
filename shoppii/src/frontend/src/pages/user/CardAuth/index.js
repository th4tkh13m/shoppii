import './index.css'
import Image from '../../../assets/images/bd2e86e454da37f2e6c9a128c8e9a2b8.png'
import { Row, Col } from 'react-bootstrap'
import { useLocation } from 'react-router-dom'
import LoginForm from '../../../components/LoginForm'
import RegisterForm from '../../../components/RegisterForm'

function CardAuth() {
    const { pathname } = useLocation()

    return (
        <Row className="wrapper">
            <Col md={6} className="left">
                <img
                    src={Image}
                    alt=""
                    style={{ height: '100%', width: '100%' }}
                />
            </Col>
            <Col md={6} className="right d-flex justify-content-center">
                <div className="d-flex flex-column">
                    {pathname === '/login' ? <LoginForm /> : <RegisterForm />}
                </div>
            </Col>
        </Row>
    )
}

export default CardAuth
