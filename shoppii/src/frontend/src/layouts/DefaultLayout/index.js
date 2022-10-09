import Navbar from '../Navbar'
import Footer from '../Footer'
import './index.css'

function DefaultLayout({ children }) {
    return (
        <div>
            <div className="w-100 div-nav-default">
                <Navbar />
            </div>
            {/* <main className="container w-100">{children}</main> */}
            <div className="w-100 div-body-default">{children}</div>
            {/* <Footer /> */}
        </div>
    )
}

export default DefaultLayout
