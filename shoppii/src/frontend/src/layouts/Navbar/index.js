import './index.css'
import Logo from '../../assets/images/—Pngtree—modern logo blue logos_7718360.png'
import {
    Search,
    ShoppingCartOutlined,
    PersonOutline,
    ListAltOutlined,
} from '@mui/icons-material'
import { Link } from 'react-router-dom'
import { Row, Container, Col, Dropdown } from 'react-bootstrap'
import { useState } from 'react'

const Navbar = () => {
<<<<<<< HEAD
    const [isAdmin, setIsAdmin] = useState(false)

    const renderNavbar = () => {
        return (
            <nav className="first-nav">
                <div className="container">
                    <header>
                        <nav className="header-navbar">
                            <div className="header-logo">
                                <img src={Logo} alt="" />
                            </div>
                            <div className="header-catalog">
                                <button>Catalog</button>
                            </div>
                            <div className="container-header-search">
                                <div className="header-search">
                                    <input
                                        type="text"
                                        placeholder="Search in the market"
                                    />
                                    <label htmlFor="">
                                        <Search
                                            className="text-white"
                                            fontSize="large"
                                        />
                                    </label>
                                </div>
                                {/* <div className="dropdown-search">
                                <a href="#">Thông tin người dùng</a>
                                <a href="#">Lịch sử mua hàng</a>
                                <a href="#">Kênh người bán</a>
                            </div> */}
                            </div>
                            <div className="header-icon">
                                <div className="header-cart">
                                    <button className="header-btn">
                                        <ShoppingCart fontSize="large" />
                                    </button>
                                </div>
                                <div className="header-user">
                                    <button
                                        className="header-btn dropdown"
                                        data-bs-toggle="dropdown"
                                    >
                                        <Person fontSize="large" />
                                    </button>
                                    <div className="dropdown-user">
                                        <a href="#">Thông tin người dùng</a>
                                        <a href="#">Lịch sử mua hàng</a>
                                        <a href="#">Kênh người bán</a>
                                    </div>
                                </div>
                            </div>
                            {/* <div className="header-guest">
                            <div className='header-guest-login'>
                                <a className='guest-link' href='#'>Login</a>
                            </div>
                            <div>
                                <a className='guest-link'>|</a>
                            </div>
                            <div className='header-guest-register'>
                                <a className='guest-link' href='#'>Register</a>
                            </div>
                        </div> */}
                        </nav>
                    </header>
                    <hr></hr>
                </div>
            </nav>
        )
    }

    const renderAdminNavbar = () => {
        return <div></div>
    }

    return <>{isAdmin ? renderAdminNavbar() : renderNavbar()}</>
=======
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
                                <div className="d-flex">
                                    <ListAltOutlined
                                        sx={{ fontSize: '20px' }}
                                    />
                                    <span className="fs-4 ms-1">Danh mục</span>
                                </div>
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
                                <div className="ms-2">
                                    <Link to="/cart">
                                        <ShoppingCartOutlined
                                            sx={{
                                                fontSize: '30px',
                                                color: 'white',
                                            }}
                                        />
                                    </Link>
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
                                        <Dropdown.Toggle>
                                            <PersonOutline
                                                sx={{
                                                    fontSize: '30px',
                                                    color: 'white',
                                                }}
                                            />
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
            {/* <Divider sx={{ border: '1px #000000 solid' }} /> */}
        </>
    )
>>>>>>> 6477ad88f80128b6bc9faf9431044aec5ba64add
}

export default Navbar
