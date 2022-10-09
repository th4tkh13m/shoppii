import Navbar from '../Navbar'
import Footer from '../Footer'
import { Container } from 'react-bootstrap'
function DefaultLayout({ children }) {
    return (
        <div>
            <div className="w-100 div-nav-default">
                <Navbar />
            </div>

            <Container
                style={{ paddingTop: '5rem' }}
                className="w-100 div-body-default"
            >
                {children}
            </Container>
        </div>
    )
}

export default DefaultLayout
