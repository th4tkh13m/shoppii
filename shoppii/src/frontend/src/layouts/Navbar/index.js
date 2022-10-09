import './index.css'
import Logo from '../../assets/images/—Pngtree—modern logo blue logos_7718360.png'
import { Search, ShoppingCart, Person } from '@mui/icons-material'
import { Link } from 'react-router-dom'
import { Row, Container, Col, Dropdown, Button } from 'react-bootstrap'
import Divider from '@mui/material/Divider'
import { useState } from 'react'

const Navbar = () => {
    const [showDropdownProfile, setShowDropdownProfile] = useState(false)
    const [showDropdownCategory, setShowDropdownCategory] = useState(false)
    const [isLogin, setIslogin] = useState(false)

    return (
        <>
            <Container fluid="md">
                <Row>
                    <Col md={3} className="col-flex">
                        <div className="header-logo">
                            <Link to="/">
                                <img src={Logo} alt="" />
                            </Link>
                        </div>
                        <Dropdown
                            onMouseEnter={() => setShowDropdownCategory(true)}
                            onMouseLeave={() => setShowDropdownCategory(false)}
                            show={showDropdownCategory}
                        >
                            <Dropdown.Toggle className="header-catalog">
                                Danh mục
                            </Dropdown.Toggle>
                            <Dropdown.Menu className="dropdown-category">
                                <Row
                                    style={{
                                        width: '100%',
                                        height: '100%',
                                        margin: 0,
                                        overflow: 'scroll',
                                        overflowX: 'hidden',
                                        width: '280px',
                                        maxHeight: '250px',
                                    }}
                                >
                                    {[
                                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
                                        13, 14,
                                    ].map(ele => {
                                        return (
                                            <>
                                                <Col md={6}>
                                                    <Dropdown.Item>
                                                        Item {ele}
                                                    </Dropdown.Item>
                                                </Col>
                                            </>
                                        )
                                    })}
                                </Row>
                            </Dropdown.Menu>
                        </Dropdown>
                    </Col>
                    <Col md={7} className="col-flex">
                        <div className="header-search">
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
                                <div>
                                    <Button variant="secondary">
                                        <Link to="/cart" className="text-white">
                                            <ShoppingCart
                                                sx={{ fontSize: '24px' }}
                                            />
                                        </Link>
                                    </Button>
                                </div>
                                <div>
                                    <Dropdown
                                        onMouseEnter={() =>
                                            setShowDropdownProfile(true)
                                        }
                                        onMouseLeave={() =>
                                            setShowDropdownProfile(false)
                                        }
                                        show={showDropdownProfile}
                                    >
                                        <Dropdown.Toggle variant="success">
                                            <Person sx={{ fontSize: '24px' }} />
                                        </Dropdown.Toggle>
                                        <Dropdown.Menu>
                                            <Dropdown.Item>
                                                <Link to="/profile">
                                                    Thông tin người dùng
                                                </Link>
                                            </Dropdown.Item>
                                            <Dropdown.Divider />
                                            <Dropdown.Item>
                                                <Link to="/profile">
                                                    Lịch sử mua hàng
                                                </Link>
                                            </Dropdown.Item>
                                            <Dropdown.Divider />
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
                            <div className="header-guest d-flex justify-content-between ">
                                <div className="header-guest-content">
                                    <Link className="guest-link" to="/login">
                                        Đăng Nhập
                                    </Link>
                                </div>
                                <div>
                                    <a className="guest-link">|</a>
                                </div>
                                <div className="header-guest-content">
                                    <Link className="guest-link" to="/register">
                                        Đăng Ký
                                    </Link>
                                </div>
                            </div>
                        )}
                    </Col>
                </Row>
            </Container>
            <Divider sx={{ border: '1px #000000 solid' }} />
        </>
    )
}

export default Navbar
