import Navbar from '../Navbar'
import Footer from '../Footer'

function DefaultLayout({ children }) {
    return (
        <div>
            <Navbar />
            <div className="content">{children}</div>
            <Footer />
        </div>
    )
}

export default DefaultLayout
