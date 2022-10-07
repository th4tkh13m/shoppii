import Navbar from '../Navbar'
import Footer from '../Footer'

function DefaultLayout({ children }) {
    return (
        <div>
            <Navbar />
            <main className="container">{children}</main>
            <Footer />
        </div>
    )
}

export default DefaultLayout
