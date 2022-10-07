import './index.css'
import { useState } from 'react'
import Logo from '../../assets/images/—Pngtree—modern logo blue logos_7718360.png'
import { Search, ShoppingCart, Person } from '@mui/icons-material'
import { Link } from 'react-router-dom'

const Navbar = () => {
    const [isLogin, setIsLogin] = useState(false)

    return (
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

                    {isLogin ? (
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
                    ) : (
                        <div className="header-guest">
                            <div className="header-guest-login">
                                <Link className="guest-link" to="/login">
                                    Login
                                </Link>
                            </div>
                            <div>
                                <span className="guest-link">|</span>
                            </div>
                            <div className="header-guest-register">
                                <Link className="guest-link" to="/register">
                                    Register
                                </Link>
                            </div>
                        </div>
                    )}
                </nav>
            </header>
            <hr></hr>
        </div>
    )
}

export default Navbar
