import Navbar from '../Navbar'
import Footer from '../Footer'

function DefaultLayout({ children }) {
    return (
        <div>
            <Navbar />
            <div className="container">{children}</div>
            <Footer />
        </div>
    )
}

export default DefaultLayout
