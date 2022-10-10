import Navbar from '../Navbar'
import Footer from '../Footer'
import { Container } from 'react-bootstrap'
function DefaultLayout({ children }) {
    return (
        <div>
            <div className="w-100 div-nav-default">
                <Navbar />
            </div>

            <div
                className="w-100 div-body-default"
                style={{ paddingTop: '5rem' }}
            >
                <Container>{children}</Container>
            </div>
        </div>
    )
}

export default DefaultLayout
