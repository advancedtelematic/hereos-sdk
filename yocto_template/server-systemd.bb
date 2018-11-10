SUMMARY = "Runs GRPC ##SERVICE_NAME Service"
LICENSE = "CLOSED"

SRC_URI = "file://##SERVICE_NAME.service"
SYSTEMD_SERVICE_${PN} = "##SERVICE_NAME.service"

inherit systemd

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    for service in ${SYSTEMD_SERVICE_${PN}}; do
        install -m 0644 ${WORKDIR}/${service} ${D}${systemd_unitdir}/system/

        sed -i -e 's,@BINDIR@,${bindir},g' ${D}${systemd_unitdir}/system/${service}
    done
}

RDEPENDS_${PN} = "##SERVICE_NAME"
