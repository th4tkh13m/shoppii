import './index.css'
import Logo from '../../assets/images/—Pngtree—modern logo blue logos_7718360.png'
import { Search, ShoppingCart, Person } from '@mui/icons-material'
import { useState } from 'react'
import { Link } from 'react-router-dom'
import { Row, Container, Col, Dropdown, Button } from 'react-bootstrap'
import Divider from '@mui/material/Divider'

const Navbar = () => {
    const [isLogin, setIslogin] = useState(true)

    return (
        <>
            <Container fluid="md">
                <Row>
                    <Col md={3} className="col-flex">
                        <div className="header-logo">
                            <img src={Logo} alt="" />
                        </div>
                        <div className="header-catalog">
                            <button>Catalog</button>
                        </div>
                    </Col>
                    <Col md={7} className="col-flex">
                        <div className="header-search w-100">
                            <input
                                type="text"
                                placeholder="Search in the market"
                            />
                            <button htmlFor="">
                                <Search
                                    className="text-white"
                                    fontSize="large"
                                />
                            </button>
                        </div>
                    </Col>
                    <Col md={2} className="col-flex">
                        {' '}
                        {isLogin ? (
                            <div className="d-flex align-items-center justify-content-around w-75 ">
                                <div className="header-cart">
                                    <Button variant="secondary">
                                        <Link to="/cart" className="text-white">
                                            <ShoppingCart fontSize="large" />
                                        </Link>
                                    </Button>
                                </div>
                                <div className="header-user">
                                    <Dropdown>
                                        <Dropdown.Toggle
                                            variant="success"
                                            id="dropdown-basic"
                                        >
                                            <Person fontSize="large" />
                                        </Dropdown.Toggle>
                                        <Dropdown.Menu>
                                            <Dropdown.Item>
                                                <Link to="/profile">
                                                    Thông tin người dùng
                                                </Link>
                                            </Dropdown.Item>
                                            <Dropdown.Item>
                                                <Link to="/profile">
                                                    Lịch sử mua hàng
                                                </Link>
                                            </Dropdown.Item>
                                            <Dropdown.Item>
                                                <Link to="/shop">
                                                    Kênh người bán
                                                </Link>
                                            </Dropdown.Item>
                                        </Dropdown.Menu>
                                    </Dropdown>
                                </div>
                            </div>
                        ) : (
                            <div className="header-guest d-flex justify-content-between ms-5">
                                <div className="header-guest-login">
                                    <Link className="guest-link" to="/login">
                                        Sign In
                                    </Link>
                                </div>
                                <div>
                                    <a className="guest-link">|</a>
                                </div>
                                <div className="header-guest-register">
                                    <Link className="guest-link" to="/register">
                                        Sign Up
                                    </Link>
                                </div>
                            </div>
                        )}
                    </Col>
                </Row>
            </Container>
            <Divider />
        </>
    )
}

export default Navbar
