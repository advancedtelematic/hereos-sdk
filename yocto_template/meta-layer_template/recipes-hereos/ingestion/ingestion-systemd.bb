SUMMARY = "Runs GRPC ingestion Service"
LICENSE = "CLOSED"

SRC_URI = "file://ingestion.service"

SYSTEMD_SERVICE_${PN} = "ingestion.service"

inherit systemd

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    for service in ${SYSTEMD_SERVICE_${PN}}; do
        install -m 0644 ${WORKDIR}/${service} ${D}${systemd_unitdir}/system/

        sed -i -e 's,@BINDIR@,${bindir},g' ${D}${systemd_unitdir}/system/${service}
    done
}

RDEPENDS_${PN} = "ingestion"
