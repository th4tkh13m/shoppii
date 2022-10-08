import Navbar from '../Navbar'
import Footer from '../Footer'

function DefaultLayout({ children }) {
    return (
        <div>
            <Navbar />
            {/* <main className="container w-100">{children}</main> */}
            {children}
            {/* <Footer /> */}
        </div>
    )
}

export default DefaultLayout
