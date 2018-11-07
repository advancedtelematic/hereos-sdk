DESCRIPTION = "##SERVICE_NAME" 
SECTION = "examples"
LICENSE = "CLOSED"
PV = "0.0.1"
PR = "r0"

SRC_URI = "file://##SERVICE_NAME-${PV}.tar.gz"

S = "${WORKDIR}/git"

DEPENDS = "grpc"

inherit pkgconfig cmake

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ##SERVICE_NAME ${D}${bindir}
}
