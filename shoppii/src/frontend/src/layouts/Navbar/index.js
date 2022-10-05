import './index.css'
import Logo from '../../assets/images/—Pngtree—modern logo blue logos_7718360.png'
import { Search, ShoppingCart, Person } from '@mui/icons-material'

function Navbar() {
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
                            <input type="text" placeholder="Search in the market" />
                            <label htmlFor="">
                                <Search className="text-white" />
                            </label>
                        </div>
                        <div className="dropdown-search">
                            <a href="#">Thông tin người dùng</a>
                            <a href="#">Lịch sử mua hàng</a>
                            <a href="#">Kênh người bán</a>
                        </div>
                    </div>

                    <div className="header-icon">
                        <div className="header-cart">
                            <button className="header-btn">
                                <ShoppingCart />
                            </button>
                        </div>
                        <div className="header-user">
                            <button className="header-btn dropdown" data-bs-toggle="dropdown">
                                <Person />
                            </button>
                            <div className="dropdown-user">
                                <a href="#">Thông tin người dùng</a>
                                <a href="#">Lịch sử mua hàng</a>
                                <a href="#">Kênh người bán</a>
                            </div>
                        </div>
                    </div>
                </nav>
            </header>

            <div className="contain">
                <div className="contain-slide"></div>
            </div>
        </div>
    )
}

export default Navbar
